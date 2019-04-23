package com.sy.communication.ws;

import static io.netty.handler.codec.http.HttpResponseStatus.BAD_REQUEST;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import java.net.InetSocketAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.sy.api.HandlerService;
import com.sy.api.MessageService;
import com.sy.api.SessionService;
import com.sy.communication.api.API;
import com.sy.pojo.Message;
import com.sy.pojo.Session;
import com.sy.utils.LogUtil;
import com.sy.utils.SpringUtil;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpUtil;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;

public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {

	private static final Logger logger = LoggerFactory.getLogger(WebSocketServerHandler.class);

	private static final SessionService sessionService = SpringUtil.getBean(SessionService.class);
	private static final HandlerService handlerService = SpringUtil.getBean(HandlerService.class);
	private static final MessageService messageService = SpringUtil.getBean(MessageService.class);

	private WebSocketServerHandshaker handshaker;

	/**
	 * channel 通道 action 活跃的 当客户端主动链接服务端的链接后，这个通道就是活跃的了。
	 * 也就是客户端与服务端建立了通信通道并且可以传输数据
	 */
	// @Override
	// public void channelActive(ChannelHandlerContext ctx) throws Exception {
	//
	// }

	/**
	 * channel 通道 Inactive 不活跃的 当客户端主动断开服务端的链接后，这个通道就是不活跃的。
	 */
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		// 删除在线记录 同时发送离线广播
		sessionService.brokenLineSession(ctx.channel());
		ctx.close();
	}

	/**
	 * 一段时间未进行读写操作 回调
	 */
	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		super.userEventTriggered(ctx, evt);
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent event = (IdleStateEvent) evt;
			/*
			 * IdleState.READER_IDLE IdleState.WRITER_IDLE IdleState.ALL_IDLE
			 */
			if (event.state().equals(IdleState.WRITER_IDLE)) {
				// 未进行写操作
				// System.out.println("=============WRITER_IDLE==============");
				// sessionService.brokenLineSession(ctx.channel());
				sessionService.brokenLine(ctx.channel());//房间离线,但不断开连接
				//				User user = sessionService.getSessionByChannel(ctx.channel()).getUser();
				//				if (user != null) {
				//					String userId = user.getUserId();
				//					roomService.brokenLine(userId);
				//				}
				// // 超时关闭channel
				// ctx.close();
			}
			// if (event.state().equals(IdleState.READER_IDLE)) {
			// System.out.println("=============READER_IDLE==============");
			// sessionService.brokenLineSession(ctx.channel());
			// // 超时关闭channel
			// ctx.close();
			// }
		}
	}

	/**
	 * 接收客户端发送的消息 channel 通道 Read 读 简而言之就是从通道中读取数据，也就是服务端接收客户端发来的数据。
	 * 但是这个数据在不进行解码时它是ByteBuf类型的
	 */
	@Override
	public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
		// 传统的HTTP接入
		if (msg instanceof FullHttpRequest) {
			handleHttpRequest(ctx, (FullHttpRequest) msg);
		}
		// WebSocket接入
		else if (msg instanceof WebSocketFrame) {
			handleWebSocketFrame(ctx, (WebSocketFrame) msg);
		}
	}

	/**
	 * channel 通道 Read 读取 Complete 完成 在通道读取完成后会在这个方法里通知。
	 */
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {

		// 如果HTTP解码失败，返回HTTP异常
		if (!req.decoderResult().isSuccess() || (!"websocket".equals(req.headers().get("Upgrade")))) {
			sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HTTP_1_1, BAD_REQUEST));
			return;
		}

		// 构造握手响应返回，本机测试
		WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory("ws://" + req.headers().get(HttpHeaderNames.HOST) + req.uri(), null, false);
		handshaker = wsFactory.newHandshaker(req);
		if (handshaker == null) {
			WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(ctx.channel());
		} else {
			handshaker.handshake(ctx.channel(), req);
			Session session = sessionService.getSessionByChannel(ctx.channel());
			String ip = req.headers().get("X-Real-IP");
			if (ip == null || ip.length() == 0) {
				InetSocketAddress insocket = (InetSocketAddress) ctx.channel().remoteAddress();
				ip = insocket.getAddress().getHostAddress();
			}
			session.setIp(ip);
		}
	}

	private void handleWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
		// 获取用户信息
		Session session = sessionService.getSessionByChannel(ctx.channel());

		// 判断是否是关闭链路的指令
		if (frame instanceof CloseWebSocketFrame) {
			handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());// TODO 注?????
			// 删除在线记录 同时发送离线广播
			sessionService.brokenLineSession(ctx.channel());
			return;
		}

		// 判断是否是Ping消息
		if (frame instanceof PingWebSocketFrame) {
			ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
			return;
		}

		// 仅支持文本消息，不支持二进制消息
		if (!(frame instanceof TextWebSocketFrame)) {
			throw new UnsupportedOperationException(String.format("%s frame types not supported", frame.getClass().getName()));
		}

		// 判断消息是否符合规则 只接受json字符串
		Message msg = messageService.getMessageByJSONString(((TextWebSocketFrame) frame).text());

		// 分发消息处理
		if (msg != null) {
			// 测试消息
			if (msg.getEvent() != API.HEARTBEAT_REQUEST) {
				String userId = null;
				if (session.getUser() != null) {
					userId = session.getUser().getUserId();
				}
				String text = "message: " + ((TextWebSocketFrame) frame).text();
				applogs(userId, text, null);
			}

			// 验证玩家是否在线
			if (!sessionService.isOnline(session, msg.getEvent())) {
				return;
			}
			// 执行消息码对应方法
			handlerService.process(session, msg);
		} else {
			// 消息非法
			ctx.channel().write(new TextWebSocketFrame("Message type is error! "));
		}
	}

	public static void send(Channel ch, Message message) {
		// 测试用打印
		if (message.getEvent() != API.HEARTBEAT_RESPONSE) {
			String userId = null;
			try {
				userId = sessionService.getSessionByChannel(ch).getUser().getUserId();
				String text = "发送消息: {" + message.getEvent() + ", " + message.getData() + "}";
				applogs(userId, text, null);
			} catch (Exception e) {

			}
		}
		if (ch.isOpen()) {
			ch.writeAndFlush(new TextWebSocketFrame(JSON.toJSONString(message)));
		}

	}

	private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, FullHttpResponse res) {
		// 返回应答给客户端
		if (res.status().code() != 200) {
			ByteBuf buf = Unpooled.copiedBuffer(res.status().toString(), CharsetUtil.UTF_8);
			res.content().writeBytes(buf);
			buf.release();
			HttpUtil.setContentLength(res, res.content().readableBytes());
		}

		// 如果是非Keep-Alive，关闭连接
		ChannelFuture f = ctx.channel().writeAndFlush(res);
		if (!HttpUtil.isKeepAlive(req) || res.status().code() != 200) {
			f.addListener(ChannelFutureListener.CLOSE);
		}
	}

	/**
	 * exception 异常 Caught 抓住 抓住异常，当发生异常的时候，可以做一些相应的处理。
	 */
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// 删除在线记录 同时发送离线广播
		//String userId = null;
//		try {
//			//userId = sessionService.getSessionByChannel(ctx.channel()).getUser().getUserId();
//			User user = sessionService.getSessionByChannel(ctx.channel()).getUser();
//			if (user != null) {
//				userId = user.getUserId();
//			}
//		} catch (NullPointerException e) {
//
//		}
		String userId = sessionService.brokenLineSession(ctx.channel());
		ctx.close();
		String text = cause.getMessage();
		if ("远程主机强迫关闭了一个现有的连接。".equals(text) || "你的主机中的软件中止了一个已建立的连接。".equals(text))
			cause = null;
		applogs(userId, text, cause);
	}

	public static void applogs(String userId, String text, Throwable t) {
		LogUtil.fileAppenderSeparate(userId, text, t);
	}
}
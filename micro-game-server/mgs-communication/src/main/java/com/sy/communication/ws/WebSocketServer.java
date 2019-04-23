package com.sy.communication.ws;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

@Component
public class WebSocketServer implements Runnable {
	
	private static final Logger logger = LoggerFactory.getLogger(WebSocketServer.class);

	private int port;

	public void setPort(int port) {
		this.port = port;
	}

	public WebSocketServer() {
		super();
	}

	public void start(int port) {
		this.port = port;
		start();
	}

	public void start() {
		try {
			logger.info("开始启动服务器 ...");
			new Thread(this).start();
		} catch (Exception e) {
			logger.error("服务器启动失败");
			e.printStackTrace();
		}
		System.out.println("***********************服务器监听端口:" + port + "完成*******************************");
		logger.info("服务器监听端口:" + port + "完成");
	}

	public void run() {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline pipeline = ch.pipeline();
					pipeline.addLast("ping", new IdleStateHandler(15, 15, 15, TimeUnit.SECONDS));
					pipeline.addLast("http-codec", new HttpServerCodec());
					pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
					ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
					pipeline.addLast("handler", new WebSocketServerHandler());
				}
			});

			Channel ch = b.bind(port).sync().channel();
			// 监听服务器关闭监听
			ch.closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}

	}

}
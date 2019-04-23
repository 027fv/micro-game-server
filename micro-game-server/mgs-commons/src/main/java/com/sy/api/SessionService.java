package com.sy.api;

import com.sy.pojo.Message;
import com.sy.pojo.Session;

import io.netty.channel.Channel;

//加在实现类
//@Service
public interface SessionService {

	/**
	 * 用户离线,关闭连接清除session,
	 * @return 登录的用户userId
	 */
	String brokenLineSession(Channel channel);

	/** 通过连接获取session */
	Session getSessionByChannel(Channel channel);

	/** 验证玩家是否在线(房间玩家显示为在线) */
	boolean isOnline(Session session, Integer event);

	/** 用户已离线状态,但不关闭连接 */
	void brokenLine(Channel channel);

	/** 全服广播消息 */
	void sendToAll(Message message);

	/** 检查玩家用户信息是否存在 */
	int isExistUser(Session session);
}

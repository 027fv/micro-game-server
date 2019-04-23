package com.sy.api;

import java.io.IOException;

import com.sy.pojo.Message;
import com.sy.pojo.Session;

public interface HandlerService {
	/**分发netty websocket消息
	 * 
	 * @param session
	 * @param msg
	 * @throws IOException 
	 */
	void process(Session session, Message msg);

}

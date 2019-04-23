package com.sy.api;

import java.io.IOException;

import com.alibaba.fastjson.JSONObject;
import com.sy.pojo.Session;
import com.sy.utils.SpringUtil;

/**
 * 消息处理接口
 * 实现时用@Processor(消息码)注释
 * spring会扫描到注释,通过applicationContext.getBeansWithAnnotation(Processor.class)获取并加载到消息分发器
 * @author fv
 *
 */
public interface MsgProcessor {

	/**消息处理方法
	 * 
	 * @param gameSession
	 * @param data
	 * @throws IOException
	 */
	public void process(Session session, JSONObject data) throws IOException;

	/**回复消息*/
	default void respond(Session session, int api, JSONObject data) {
		MessageService messageService = SpringUtil.getBean(MessageService.class);
		messageService.sendMessage(session, api, data);
	}

	/**返回警告*/
	default void respondWarn(Session session, int code, String msg) {
		MessageService messageService = SpringUtil.getBean(MessageService.class);
		messageService.sendWarn(session, code, msg);
	}

}

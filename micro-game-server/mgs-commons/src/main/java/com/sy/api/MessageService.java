package com.sy.api;

import com.alibaba.fastjson.JSONObject;
import com.sy.pojo.Message;
import com.sy.pojo.Session;

public interface MessageService {

	/**验证JSON字符串是否符合消息规则*/
	public Message getMessageByJSONString(String message);

	/**发送消息*/
	public void sendMessage(Session session, int api, JSONObject data);

	/**发送消息*/
	public void sendMessage(Session session, int api, String message);

	/**发送消息*/
	public void sendMessage(Session session, Message message);

	/**发送警告*/
	public void sendWarn(Session session, int code, String msg);

	/**附带警告*/
	public void addWarn(JSONObject sendJson, int code, String msg);
	
}

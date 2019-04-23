package com.sy.pojo;

import java.io.Serializable;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * websocket消息封装
 * @author fv
 *
 */
public class Message implements Serializable {

	private static final long serialVersionUID = 5076525843390974550L;

	private int event; //websocket消息头
	private JSONObject data = null; //websocket消息内容

	public int getEvent() {
		return event;
	}

	public void setEvent(int event) {
		this.event = event;
	}

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}

	public Message() {
		super();
	}

	public Message(String message) {
		super();
		JSONObject jsonObject = JSON.parseObject(message);
		this.event = jsonObject.getInteger("event");
		this.data = jsonObject.getJSONObject("data");
	}

	public Message(int event, JSONObject data) {
		super();
		this.event = event;
		this.data = data;
	}

	public Message(JSONObject message) {
		super();
		this.event = message.getInteger("event");
		this.data = message.getJSONObject("data");
	}
}

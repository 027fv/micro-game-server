package com.sy.pojo;

import java.io.Serializable;
import java.nio.channels.Channel;

/**
 * 用户session
 * @author fv
 *
 */
public class Session implements Serializable {

	private static final long serialVersionUID = -2806283200219580528L;

	private Channel channel;
	/** 玩家个人数据 */
	private User user;
	/** 连接ip */
	private String ip;//= ""

	private long heatBest = System.currentTimeMillis();

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public long getHeatBest() {
		return heatBest;
	}

	public void setHeatBest(long heatBest) {
		this.heatBest = heatBest;
	}

	public Session() {
		super();
	}

	public Session(Channel channel) {
		super();
		this.channel = channel;
	}

}

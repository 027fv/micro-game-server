package com.sy.pojo;

import java.io.Serializable;

/**
 * 游戏公告
 * @author yy
 * */
public class Notice implements Serializable {
	private static final long serialVersionUID = 4984927699244344942L;

	private Integer id; //
	private String notice; //消息内容

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}
}

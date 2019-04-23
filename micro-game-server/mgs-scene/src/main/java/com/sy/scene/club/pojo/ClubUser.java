package com.sy.scene.club.pojo;

import java.io.Serializable;

/**
 * 茶楼用户信息
 * */
public class ClubUser implements Serializable {

	private static final long serialVersionUID = 1359898109837549420L;

	private String clubId; //茶楼id
	private String userId; //用户id
	private Integer isApprove; //是否通过审核
	private Integer isManager; //是否为管理
	private Integer bigWin; //在茶楼中的获胜次数

	private String name; //用户昵称
	private String headimg; //用户头像	

	public String getClubId() {
		return clubId;
	}

	public void setClubId(String clubId) {
		this.clubId = clubId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getIsApprove() {
		return isApprove;
	}

	public void setIsApprove(Integer isApprove) {
		this.isApprove = isApprove;
	}

	public Integer getIsManager() {
		return isManager;
	}

	public void setIsManager(Integer isManager) {
		this.isManager = isManager;
	}

	public Integer getBigWin() {
		return bigWin;
	}

	public void setBigWin(Integer bigWin) {
		this.bigWin = bigWin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

}

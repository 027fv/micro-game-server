package com.sy.scene.club.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 茶楼信息
 */
public class Club implements Serializable {

	private static final long serialVersionUID = 1L;

	private String clubId; // 茶楼id
	private Timestamp createTime; // 茶楼创建时间
	private String notice; // 茶楼公告
	
	private String clubOwner; // 茶楼主id
	private String name; // 用户昵称
	private String headimg; // 用户头像
	private int active; // 活跃度
	
	private Integer clubGameType; // 茶楼游戏类型
	private String clubConfig; // 茶楼其他参数 为JSON字符串
	private Integer needApprove; // 是否需要审核
	private Integer consume; // 开房消耗


	private Integer clubUserNum;

	public String getClubId() {
		return clubId;
	}

	public void setClubId(String clubId) {
		this.clubId = clubId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public Integer getClubGameType() {
		return clubGameType;
	}

	public void setClubGameType(Integer clubGameType) {
		this.clubGameType = clubGameType;
	}

	public String getClubConfig() {
		return clubConfig;
	}

	public void setClubConfig(String clubConfig) {
		this.clubConfig = clubConfig;
	}

	public Integer getNeedApprove() {
		return needApprove;
	}

	public void setNeedApprove(Integer needApprove) {
		this.needApprove = needApprove;
	}

	public Integer getConsume() {
		return consume;
	}

	public void setConsume(Integer consume) {
		this.consume = consume;
	}

	public String getClubOwner() {
		return clubOwner;
	}

	public void setClubOwner(String clubOwner) {
		this.clubOwner = clubOwner;
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

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Integer getClubUserNum() {
		return clubUserNum;
	}

	public void setClubUserNum(Integer clubUserNum) {
		this.clubUserNum = clubUserNum;
	}
	
	

}

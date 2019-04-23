package com.sy.scene.club.pojo;

import java.io.Serializable;

/**
 * 茶楼茶桌信息
 * */
public class ClubRoom implements Serializable {

	private static final long serialVersionUID = -3661428438429229689L;

	private String clubId; //茶楼id
	private Integer clubTableIndex; //茶楼茶桌号
	private String roomId; //房间id

	public String getClubId() {
		return clubId;
	}

	public void setClubId(String clubId) {
		this.clubId = clubId;
	}

	public Integer getClubTableIndex() {
		return clubTableIndex;
	}

	public void setClubTableIndex(Integer clubTableIndex) {
		this.clubTableIndex = clubTableIndex;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public ClubRoom(String clubId, Integer clubTableIndex, String roomId) {
		super();
		this.clubId = clubId;
		this.clubTableIndex = clubTableIndex;
		this.roomId = roomId;
	}

}

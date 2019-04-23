package com.sy.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class Relevant implements Serializable {

	private static final long serialVersionUID = 2083064800360773888L;

	private String uuid;
	private String roomId;
	private String houseId;
	private Integer houseTableIndex;
	private String relevant; // 房间相关者
	private String totalScore; // 游戏总得分
	private Timestamp endTime; // 结束时间
	private int gameType; // 游戏类型

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getHouseId() {
		return houseId;
	}

	public void setHouseId(String houseId) {
		this.houseId = houseId;
	}

	public Integer getHouseTableIndex() {
		return houseTableIndex;
	}

	public void setHouseTableIndex(Integer houseTableIndex) {
		this.houseTableIndex = houseTableIndex;
	}

	public String getRelevant() {
		return relevant;
	}

	public void setRelevant(String relevant) {
		this.relevant = relevant;
	}

	public String getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(String totalScore) {
		this.totalScore = totalScore;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public int getGameType() {
		return gameType;
	}

	public void setGameType(int gameType) {
		this.gameType = gameType;
	}

	public Relevant() {
		super();
	}

	public Relevant(String uuid, String roomId, String houseId, Integer houseTableIndex, String relevant, String totalScore, Timestamp endTime, int gameType) {
		super();
		this.uuid = uuid;
		this.roomId = roomId;
		this.houseId = houseId;
		this.houseTableIndex = houseTableIndex;
		this.relevant = relevant;
		this.totalScore = totalScore;
		this.endTime = endTime;
		this.gameType = gameType;
	}

}

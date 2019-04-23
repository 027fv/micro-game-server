package com.sy.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.alibaba.fastjson.JSONObject;

public class RoomSeat implements Serializable {

	private static final long serialVersionUID = -6033261942782529023L;

	private String roomId; // 房间Id
	private String userId; // 玩家Id
	private Integer userSeatIndex; // 玩家座位号
	private boolean leave; // 玩家离线状态

	private String name; // 用户昵称
	private Integer sex; // 用户性别
	private String headimg; // 用户头像
	private String ip; // 用户ip
	private String gps; // 用户gps
	private float totalScore = 0f; // 总得分
	private Double coins = 0d;

	private int gamesNum; //用户游戏次数
	private int winNum; //用户游戏次数

	public float getTotalScore() {
		BigDecimal bd = new BigDecimal(totalScore);
		bd = bd.setScale((int) Math.ceil(Math.abs(totalScore) % 1) * 3, RoundingMode.HALF_UP);
		return bd.floatValue();
	}

	public RoomSeat(RoomSeat seat) {
		super();
		this.roomId = seat.roomId;
		this.userId = seat.userId;
		this.leave = false;
	}

	public RoomSeat(String roomId, User user) {
		super();
		this.roomId = roomId;
		this.userId = user.getUserId();
		this.leave = false;
		this.name = user.getName();
		this.sex = user.getSex();
		this.headimg = user.getHeadimg();
		this.gps = user.getGps();
		//this.ip = SessionMgr.getSession(userId).getIp();
	}
	public RoomSeat(String roomId, String userId) {
		super();
		this.roomId = roomId;
		this.userId = userId;
		this.leave = false;
//		User user = UserMgr.getUserById(userId);
//		this.name = user.getName();
//		this.sex = user.getSex();
//		this.headimg = user.getHeadimg();
//		this.gps = user.getGps();
		//this.ip = SessionMgr.getSession(userId).getIp();
	}

	public void setRoomSeat(RoomSeat roomSeat) {
		this.roomId = roomSeat.roomId;
		this.userId = roomSeat.userId;
		this.leave = roomSeat.leave;
		this.name = roomSeat.name;
		this.sex = roomSeat.sex;
		this.headimg = roomSeat.headimg;
		this.gps = roomSeat.gps;
		//this.ip = SessionMgr.getSession(userId).getIp();
	}

	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		json.put("roomId", roomId);
		json.put("userId", userId);
		json.put("userSeatIndex", userSeatIndex);
		json.put("leave", leave);
		json.put("name", name);
		json.put("sex", sex);
		json.put("headimg", headimg);
		json.put("ip", ip);
		json.put("gps", gps);
		//Room room = RoomMgr.getRoom(roomId);
		json.put("coins", coins);
//		if (room.isUpCoins()) {
//			json.put("totalScore", coins);
//		} else {
//			json.put("totalScore", totalScore);
//		}
		json.put("totalScore2", totalScore);
		//json.put("totalScore", totalScore);
		json.put("coins", coins);
		json.put("gamesNum", gamesNum);
		json.put("winNum", winNum);
		return json;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getUserSeatIndex() {
		return userSeatIndex;
	}

	public void setUserSeatIndex(Integer userSeatIndex) {
		this.userSeatIndex = userSeatIndex;
	}

	public boolean isLeave() {
		return leave;
	}

	public void setLeave(boolean leave) {
		this.leave = leave;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getGps() {
		return gps;
	}

	public void setGps(String gps) {
		this.gps = gps;
	}

	public Double getCoins() {
		return coins;
	}

	public void setCoins(Double coins) {
		this.coins = coins;
	}

	public int getGamesNum() {
		return gamesNum;
	}

	public void setGamesNum(int gamesNum) {
		this.gamesNum = gamesNum;
	}

	public int getWinNum() {
		return winNum;
	}

	public void setWinNum(int winNum) {
		this.winNum = winNum;
	}

	public void setTotalScore(float totalScore) {
		this.totalScore = totalScore;
	}

}

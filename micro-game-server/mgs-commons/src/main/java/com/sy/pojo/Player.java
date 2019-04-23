package com.sy.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 玩家基类
 * 每局游戏时 玩家数据储存
 * */

public class Player implements Serializable {

	private static final long serialVersionUID = 4656633608906308069L;
	private String userId; //玩家Id
	private String userName; //玩家昵称
	private Integer seatIndex; //玩家座位号
	private float score; //此局得分
	private double coin; //玩家金币

	public float getScore() {
		BigDecimal bd = new BigDecimal(score);
		bd = bd.setScale((int) Math.ceil(Math.abs(score) % 1) * 3, RoundingMode.HALF_UP);
		return bd.floatValue();
	}

	public double getCoin() {
		BigDecimal bd = new BigDecimal(coin);
		bd = bd.setScale((int) Math.ceil(Math.abs(coin) % 1) * 3, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}

	public void setCoin(double coin) {
		this.coin = coin;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getSeatIndex() {
		return seatIndex;
	}

	public void setSeatIndex(Integer seatIndex) {
		this.seatIndex = seatIndex;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public Player(String userId, String userName, Integer seatIndex) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.seatIndex = seatIndex;
	}

}

package com.sy.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.alibaba.fastjson.JSONObject;

public class Setting implements Serializable {

	private static final long serialVersionUID = -4514060696930131696L;

	private int gameType = 1; // 游戏类型
	private int maxOfTurns = 8; // 最大局数
	private boolean aaPayment = false;// 房费1房主支付,2四人支付
	private int costGems; // 房卡消耗
	private float base = 1f; // 底分
	private String roomConfig; // 房间其他信息 为JSON字符串
	private JSONObject conf;// 房间其他信息 为JSON对象
	private int playerMax = 4;// 最大游戏人数
	private int fishing = 0;// 钓鱼玩家
	private boolean autoSitDown = false;// 自动入座
	private boolean exchangeSeat = false;// 中场换位
	private int dismissCost = 0;// 解散人承担其他负分玩家部分(0,1/4,1/2,1)的负分数
	private Boolean dismissMustManager;// 只有茶楼主可以解散
	private int choosePiao = 0;// 选飘:0不飘;1首局选飘;2每局选飘
	private boolean releaseEmptyRoom = true;// 空房间实时回收

	public float getBase() {
		BigDecimal bd = new BigDecimal(base);
		// bd = bd.setScale(1, BigDecimal.ROUND_HALF_UP);
		bd = bd.setScale((int) Math.ceil(Math.abs(base) % 1) * 3, RoundingMode.HALF_UP);
		return bd.floatValue();
	}

	public Setting(int gameType, JSONObject conf) {
		super();
		this.gameType = gameType;
		this.conf = conf;
		this.roomConfig = conf.toJSONString();
		this.maxOfTurns = (2 == conf.getIntValue("roundNum") ? 16 : 8);
		this.aaPayment = (2 == conf.getIntValue("roomPayment"));
		this.playerMax = (conf.getInteger("playerMax") == null ? 4 : conf.getIntValue("playerMax"));
		this.costGems = (aaPayment ? maxOfTurns / 8 : maxOfTurns / 8 * playerMax);
		//this.costGems = conf.getInteger("costGems");
		this.base = (conf.getFloat("baseScore") == null ? 1f : conf.getFloatValue("baseScore"));
		this.fishing = conf.getIntValue("fishing") % (playerMax + 1);
		this.autoSitDown = conf.getBooleanValue("autoSitDown");
		// this.exchangeSeat = conf.getBooleanValue("exchangeSeat");
		this.choosePiao = conf.getIntValue("choosePiao");
		this.dismissCost = conf.getIntValue("dismissCost");
		this.dismissMustManager = conf.getBooleanValue("dismissMustManager");
	}

	public JSONObject toJSON() {
		JSONObject json = new JSONObject();
		json.put("gameType", gameType); // 游戏类型
		json.put("maxOfTurns", maxOfTurns); // 最大局数
		json.put("aaPayment", aaPayment);// 房费1房主支付,2四人支付
		json.put("costGems", costGems); // 房卡消耗
		json.put("base", base); // 底分
		json.put("roomConfig", roomConfig); // 房间其他信息 为JSON字符串
		json.put("conf", conf);// 房间其他信息 为JSON对象
		json.put("playerMax", playerMax);// 最大游戏人数
		json.put("fishing", fishing);// 钓鱼玩家
		json.put("autoSitDown", autoSitDown);// 自动入座
		json.put("exchangeSeat", exchangeSeat);// 中场换位
		json.put("dismissCost", dismissCost);// 解散人承担其他负分玩家部分(0,1/4,1/2,1)的负分数
		json.put("dismissMustManager", dismissMustManager);// 只有茶楼主可以解散
		json.put("choosePiao", choosePiao);// 选飘:0不飘;1首局选飘;2每局 );飘
		json.put("releaseEmptyRoom", releaseEmptyRoom);// 空房间实时回收
		return json;
	}

	public int getGameType() {
		return gameType;
	}

	public void setGameType(int gameType) {
		this.gameType = gameType;
	}

	public int getMaxOfTurns() {
		return maxOfTurns;
	}

	public void setMaxOfTurns(int maxOfTurns) {
		this.maxOfTurns = maxOfTurns;
	}

	public boolean isAaPayment() {
		return aaPayment;
	}

	public void setAaPayment(boolean aaPayment) {
		this.aaPayment = aaPayment;
	}

	public int getCostGems() {
		return costGems;
	}

	public void setCostGems(int costGems) {
		this.costGems = costGems;
	}

	public String getRoomConfig() {
		return roomConfig;
	}

	public void setRoomConfig(String roomConfig) {
		this.roomConfig = roomConfig;
	}

	public JSONObject getConf() {
		return conf;
	}

	public void setConf(JSONObject conf) {
		this.conf = conf;
	}

	public int getPlayerMax() {
		return playerMax;
	}

	public void setPlayerMax(int playerMax) {
		this.playerMax = playerMax;
	}

	public int getFishing() {
		return fishing;
	}

	public void setFishing(int fishing) {
		this.fishing = fishing;
	}

	public boolean isAutoSitDown() {
		return autoSitDown;
	}

	public void setAutoSitDown(boolean autoSitDown) {
		this.autoSitDown = autoSitDown;
	}

	public boolean isExchangeSeat() {
		return exchangeSeat;
	}

	public void setExchangeSeat(boolean exchangeSeat) {
		this.exchangeSeat = exchangeSeat;
	}

	public int getDismissCost() {
		return dismissCost;
	}

	public void setDismissCost(int dismissCost) {
		this.dismissCost = dismissCost;
	}

	public Boolean getDismissMustManager() {
		return dismissMustManager;
	}

	public void setDismissMustManager(Boolean dismissMustManager) {
		this.dismissMustManager = dismissMustManager;
	}

	public int getChoosePiao() {
		return choosePiao;
	}

	public void setChoosePiao(int choosePiao) {
		this.choosePiao = choosePiao;
	}

	public boolean isReleaseEmptyRoom() {
		return releaseEmptyRoom;
	}

	public void setReleaseEmptyRoom(boolean releaseEmptyRoom) {
		this.releaseEmptyRoom = releaseEmptyRoom;
	}

	public void setBase(float base) {
		this.base = base;
	}

}

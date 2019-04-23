package com.sy.pojo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sy.api.State;

public class Room extends Setting implements Serializable {

	private static final long serialVersionUID = -3004894687260858358L;

	private String uuid; // uuid

	private String roomId; // 房间号

	private String owner; // 房主Id

	private String ownerName; // 房主昵称

	private Timestamp createTime; // 房间创建时间

	private int numOfTurns; // 游戏局数

	private int[] ready; // 房间中玩家的准备信息

	private int[] piaofen; // 房间中玩家的飘分信息

	private State state = GameState.IDLE; // 游戏状态

	private int active; // 房间活跃

	private int playerNum;// 座上玩家数

	private Map<String, RoomSeat> seats; // 房间所有人(key为userId)(自然数:玩家;null:观众;负数:钓鱼)

	protected List<? extends Player> players; // 牌桌上的玩家(有序)

	protected List<Player> fishingPlayers = new ArrayList<Player>(); // 钓鱼位上的玩家(有序)

	protected List<RoomSeat> fishingSeats = new ArrayList<RoomSeat>(); // 参与钓鱼者(选择钓鱼时顺序)

	private boolean upCoins;

	private Integer admission;

	public Integer getRoomSeatIndexByUserId(String userId) {
		if (this.seats.get(userId) != null) {
			return this.seats.get(userId).getUserSeatIndex();
		}
		return null;
	}

	public void setState(State gameState) {
		this.state = gameState;
	}

	public void setState(String stateStr) {
		this.state = State.getState(stateStr);
	}

	public JSONObject toJSON() {
		return (JSONObject) JSON.toJSON(this);
	}

	public Room(int gameType, JSONObject conf) {
		super(gameType, conf);
	}

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

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public int getNumOfTurns() {
		return numOfTurns;
	}

	public void setNumOfTurns(int numOfTurns) {
		this.numOfTurns = numOfTurns;
	}

	public int[] getReady() {
		return ready;
	}

	public void setReady(int[] ready) {
		this.ready = ready;
	}

	public int[] getPiaofen() {
		return piaofen;
	}

	public void setPiaofen(int[] piaofen) {
		this.piaofen = piaofen;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getPlayerNum() {
		return playerNum;
	}

	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}

	public Map<String, RoomSeat> getSeats() {
		return seats;
	}

	public void setSeats(Map<String, RoomSeat> seats) {
		this.seats = seats;
	}

	public List<? extends Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<? extends Player> players) {
		this.players = players;
	}

	public List<Player> getFishingPlayers() {
		return fishingPlayers;
	}

	public void setFishingPlayers(List<Player> fishingPlayers) {
		this.fishingPlayers = fishingPlayers;
	}

	public List<RoomSeat> getFishingSeats() {
		return fishingSeats;
	}

	public void setFishingSeats(List<RoomSeat> fishingSeats) {
		this.fishingSeats = fishingSeats;
	}

	public boolean isUpCoins() {
		return upCoins;
	}

	public void setUpCoins(boolean upCoins) {
		this.upCoins = upCoins;
	}

	public Integer getAdmission() {
		return admission;
	}

	public void setAdmission(Integer admission) {
		this.admission = admission;
	}

	public State getState() {
		return state;
	}

}

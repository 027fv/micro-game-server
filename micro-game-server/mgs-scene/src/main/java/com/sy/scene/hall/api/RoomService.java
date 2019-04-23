package com.sy.scene.hall.api;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.sy.pojo.Message;
import com.sy.pojo.Room;
import com.sy.pojo.RoomSeat;

public interface RoomService {

	/** 获取用户房间的座位 */
	RoomSeat getSeat(String userId);

	/** 取消等待中的玩家的准备 */
	void cancelReady(String userId);

	/** 给房间广播消息 */
	void broadcast(String roomId, Message message);

	/**
	 * 创建房间
	 * 
	 * @param userId   房主id
	 * @param gameType 游戏类型
	 * @param scene    场景(大厅/金币场/训练场/比赛场/俱乐部/上分俱乐部...)
	 * @param config   创房参数
	 * @return
	 */
	Room createRoom(String userId, Integer gameType, String scene, JSONObject config);

	/**
	 * 解散未开始的游戏房间
	 * 
	 * @param userId    用户id
	 * @param interrupt 是否中断游戏
	 * @return
	 */
	List<String> dissolveRoom(String userId, boolean interrupt);

	/**
	 * 玩家退出房间
	 * 
	 * @param userId
	 * @return
	 */
	boolean exitRoom(String userId);

}

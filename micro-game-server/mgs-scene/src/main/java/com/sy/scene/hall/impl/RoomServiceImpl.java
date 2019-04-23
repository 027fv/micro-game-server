package com.sy.scene.hall.impl;

import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.sy.pojo.Message;
import com.sy.pojo.Room;
import com.sy.pojo.RoomSeat;
import com.sy.scene.hall.api.RoomService;

public class RoomServiceImpl implements RoomService{

	@Override
	public RoomSeat getSeat(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancelReady(String userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void broadcast(String roomId, Message message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Room createRoom(String userId, Integer gameType, String scene, JSONObject config) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> dissolveRoom(String userId, boolean interrupt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exitRoom(String userId) {
		// TODO Auto-generated method stub
		return false;
	}

}

package com.sy.scene.hall.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.sy.pojo.Room;

public class RoomCache {
	/**
	 * 所有游戏中房间信息
	 * 
	 * @KEY: roomId
	 * @VALUE: Room
	 */
	private static Map<String, Room> rooms = new ConcurrentHashMap<String, Room>();

	public static Room putRoom(Room room) {
		return rooms.put(room.getRoomId(), room);
	}

	public static int size() {
		return rooms.size();
	}

	public static Room getRoom(String roomId) {
		return rooms.get(roomId);
	}

	public static Map<String, Room> getRooms() {
		return rooms;
	}

	public static Room removeRoom(String roomId) {
		return rooms.remove(roomId);
	}

	public static boolean removeRoom(Room room) {
		return rooms.remove(room.getRoomId(), room);
	}

	public static void clearRoom() {
		rooms.clear();
	}
}

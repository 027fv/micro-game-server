package com.sy.scene.hall.cache;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.sy.pojo.Room;

/**
 * 玩家创建的房间
 * 
 * @KEY: userId
 * @VALUE: List<Room>
 */
public class UserRoomCache {

	private static final Map<String, List<Room>> userRooms = new ConcurrentHashMap<String, List<Room>>();

	public static boolean putRoom(Room room) {
		String owner = room.getOwner();
		List<Room> rooms = getRooms(owner);
		if (rooms == null) {
			rooms = new LinkedList<Room>();
		}
		return rooms.add(room);
	}

	public static int size() {
		return userRooms.size();
	}

	public static int size(String userId) {
		return getRooms(userId).size();
	}

	public static Map<String, List<Room>> getRooms() {
		return userRooms;
	}

	public static List<Room> getRooms(String userId) {
		return userRooms.get(userId);
	}

	public static List<Room> removeRooms(String userId) {
		return userRooms.remove(userId);
	}

	public static boolean removeRoom(String userId, Room room) {
		return userRooms.get(userId).remove(room);
	}

	public static void clearRoomSeat() {
		userRooms.clear();
	}

}

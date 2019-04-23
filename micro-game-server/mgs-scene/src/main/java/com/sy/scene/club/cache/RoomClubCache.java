package com.sy.scene.club.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.sy.scene.club.pojo.ClubRoom;

/**
 * 所有茶楼房间数据
 * 
 * @KEY: roomId
 * @value: ClubRoom
 */
public class RoomClubCache {

	private static final Map<String, ClubRoom> clubRooms = new ConcurrentHashMap<String, ClubRoom>();

	public static ClubRoom put(ClubRoom clubRoom) {
		return clubRooms.put(clubRoom.getRoomId(), clubRoom);
	}

	public static int size() {
		return clubRooms.size();
	}

	public static ClubRoom get(String roomId) {
		return clubRooms.get(roomId);
	}

	public static Map<String, ClubRoom> getAll() {
		return clubRooms;
	}

	public static ClubRoom remove(String roomId) {
		return clubRooms.remove(roomId);
	}

	public static boolean remove(ClubRoom clubRoom) {
		return clubRooms.remove(clubRoom.getRoomId(), clubRoom);
	}

	public static void clear() {
		clubRooms.clear();
	}

}

package com.sy.scene.hall.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.sy.pojo.RoomSeat;

public class SeatCache {

	/**
	 * 所有游戏中座位信息
	 * 
	 * @KEY: userId
	 * @VALUE: RoomSeat
	 */
	private static Map<String, RoomSeat> seats = new ConcurrentHashMap<String, RoomSeat>();

	public static RoomSeat putRoomSeat(RoomSeat seat) {
		return seats.put(seat.getUserId(), seat);
	}

	public static int size() {
		return seats.size();
	}

	public static RoomSeat getRoomSeat(String userId) {
		return seats.get(userId);
	}

	public static Map<String, RoomSeat> getRoomSeats() {
		return seats;
	}

	public static RoomSeat removeRoomSeat(String userId) {
		return seats.remove(userId);
	}

	public static boolean removeRoomSeat(RoomSeat seat) {
		return seats.remove(seat.getUserId(), seat);
	}

	public static void clearRoomSeat() {
		seats.clear();
	}

}

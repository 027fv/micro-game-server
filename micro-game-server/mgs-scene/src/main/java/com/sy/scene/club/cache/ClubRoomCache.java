package com.sy.scene.club.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.sy.scene.club.pojo.ClubRoom;

/**
 * 茶楼中所有房间数据
 * 
 * @KEY: clubId
 * @Value: ArrayList<@Value ClubRoom>
 */
public class ClubRoomCache {

	private static final Map<String, List<ClubRoom>> clubRooms = new ConcurrentHashMap<String, List<ClubRoom>>();

	public static List<ClubRoom> put(ClubRoom clubRoom) {
		String clubId = clubRoom.getClubId();
		Integer index = clubRoom.getClubTableIndex();
		List<ClubRoom> list = get(clubId);
		if (list == null) {
			list = new ArrayList<ClubRoom>();
		}
		while (list.size() <= index) {
			list.add(null);
		}
		list.set(index, clubRoom);
		return clubRooms.put(clubId, list);
	}

	public static int size(String clubId) {
		return get(clubId).size();
	}

	public static int size() {
		return clubRooms.size();
	}

	public static ClubRoom get(String clubId, int index) {
		return clubRooms.get(clubId).get(index);
	}

	public static List<ClubRoom> get(String clubId) {
		return clubRooms.get(clubId);
	}

	public static Map<String, List<ClubRoom>> getAll() {
		return clubRooms;
	}

	public static List<ClubRoom> remove(String clubId) {
		return clubRooms.remove(clubId);
	}

	public static ClubRoom remove(String clubId, int index) {
		return get(clubId).set(index, null);
	}

	public static ClubRoom remove(ClubRoom clubRoom) {
		return remove(clubRoom.getClubId(), clubRoom.getClubTableIndex());
	}

	public static void clear() {
		clubRooms.clear();
	}

}

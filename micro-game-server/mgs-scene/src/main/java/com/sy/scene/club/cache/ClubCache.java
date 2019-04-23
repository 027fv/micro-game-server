package com.sy.scene.club.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.sy.scene.club.pojo.Club;

public class ClubCache {
	/**
	 * 所有已创建茶楼数据
	 * 
	 * @KEY: clubId
	 * @value: Club
	 */
	private static final Map<String, Club> clubs = new ConcurrentHashMap<String, Club>();

	public static Club put(Club club) {
		return clubs.put(club.getClubId(), club);
	}

	public static int size() {
		return clubs.size();
	}

	public static Club get(String clubId) {
		return clubs.get(clubId);
	}

	public static Map<String, Club> getAll() {
		return clubs;
	}

	public static Club remove(String clubId) {
		return clubs.remove(clubId);
	}

	public static boolean remove(Club club) {
		return clubs.remove(club.getClubId(), club);
	}

	public static void clear() {
		clubs.clear();
	}
	
}

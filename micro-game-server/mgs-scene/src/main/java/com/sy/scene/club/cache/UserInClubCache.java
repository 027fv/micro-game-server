package com.sy.scene.club.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 玩家所在的茶楼
 * 
 * @KEY: userId
 * @value: clubId
 */
public class UserInClubCache {
	
	private static final Map<String, String> userInClubs = new ConcurrentHashMap<String, String>();

	public static String put(String userId, String clubId) {
		return userInClubs.put(userId, clubId);
	}

	public static int size() {
		return userInClubs.size();
	}

	public static String get(String userId) {
		return userInClubs.get(userId);
	}

	public static Map<String, String> getAll() {
		return userInClubs;
	}

	public static String remove(String userId) {
		return userInClubs.remove(userId);
	}

	public static boolean remove(String userId, String clubId) {
		return userInClubs.remove(userId, clubId);
	}

	public static void clear() {
		userInClubs.clear();
	}

}

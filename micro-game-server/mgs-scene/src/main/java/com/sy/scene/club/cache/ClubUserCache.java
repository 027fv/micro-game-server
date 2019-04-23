package com.sy.scene.club.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.sy.scene.club.pojo.ClubUser;

/**
 * 茶楼中的玩家数据
 * 
 * @KEY: clubId
 * @Value: Map<@KEY userId @Value ClubUser>
 */
public class ClubUserCache {

	private static final Map<String, Map<String, ClubUser>> clubUsers = new ConcurrentHashMap<String, Map<String, ClubUser>>();

	public static Map<String, ClubUser> put(ClubUser clubUser) {
		String clubId = clubUser.getClubId();
		String userId = clubUser.getUserId();
		Map<String, ClubUser> list = get(clubId);
		if (list == null) {
			list = new ConcurrentHashMap<String, ClubUser>();
		}
		list.put(userId, clubUser);
		return clubUsers.put(clubId, list);
	}

	public static int size(String clubId) {
		return get(clubId).size();
	}

	public static int size() {
		return clubUsers.size();
	}

	public static Map<String, ClubUser> get(String clubId) {
		return clubUsers.get(clubId);
	}

	public static Map<String, Map<String, ClubUser>> getAll() {
		return clubUsers;
	}

	public static Map<String, ClubUser> removeClubUser(String clubId) {
		return clubUsers.remove(clubId);
	}

	public static ClubUser remove(String clubId, String userId) {
		return get(clubId).remove(userId);
	}

	public static ClubUser remove(ClubUser clubUser) {
		return remove(clubUser.getClubId(), clubUser.getUserId());
	}

	public static void clear() {
		clubUsers.clear();
	}

}

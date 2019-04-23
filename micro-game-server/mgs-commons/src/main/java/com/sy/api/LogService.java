package com.sy.api;

import java.util.Map;

public interface LogService {

	/**获取玩家context信息,用于记录日志
	 * 
	 * @param userId
	 * @return {"poject":项目名,"scene":场景(hall/club/room...),"game":游戏类型/名,"date":房间创建的日期字符串,"position":房间号or茶楼号-桌号,"uuid":房间的uuid,"seatIndex":座位号}
	 */
	Map<String, String> getContextMap(String userId);

}

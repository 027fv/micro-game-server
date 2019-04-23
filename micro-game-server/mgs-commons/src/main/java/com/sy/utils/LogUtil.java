package com.sy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import com.sy.api.LogService;

public class LogUtil {

	public static void fileAppenderSeparate(String userId, String msg, Throwable t) {
		LogService logService = SpringUtil.getBean(LogService.class);
		Map<String, String> contextMap = logService.getContextMap(userId);
		//项目名
		String poject = contextMap.get("poject");
		StringUtil.setValueDefaultIfBlank(poject, "poject");
		//场景
		String scene = contextMap.get("scene");
		StringUtil.setValueDefaultIfBlank(scene, "scene");
		//游戏类型/游戏名
		String game = contextMap.get("game");
		//StringUtil.setValueDefaultIfBlank(game, "game");
		//房间创建的日期字符串
		String date = contextMap.get("date");
		StringUtil.setValueDefaultIfBlank(date, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		//房间号or茶楼号-桌号
		String position = contextMap.get("position");
		//StringUtil.setValueDefaultIfBlank(position, "position");
		//房间的uuid
		String uuid = contextMap.get("uuid");
		StringUtil.setValueDefaultIfBlank(uuid, "uuid");
		//座位号
		String seatIndex = contextMap.get("seatIndex");
		String suffix = (userId == null ? "" : " userId: " + userId + " ") + (seatIndex == null ? "" : " seatIndex: " + seatIndex + " ");
		if (StringUtil.isNotBlank(suffix)) {
			msg += "[" + suffix + "]";
		}
		String mdc_file = poject + "/" + scene + "/" + (StringUtil.isBlank(game) ? "" : game + "/") + date + "/" + (StringUtil.isBlank(position) ? "" : position + "/") + (StringUtil.isBlank(uuid) ? "" : uuid + "/");
		MDC.put("mdc_file", mdc_file);
		Logger log = LoggerFactory.getLogger("mdc_Log");
		if (t == null) {
			log.info(msg);
		} else {
			log.error(msg, t);
		}
	}
}

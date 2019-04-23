package com.sy.scene.hall.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.sy.pojo.Room;
import com.sy.scene.hall.api.RoomService;

/**
 * 提供REST api远程服务
 * @author fv
 */
@RestController
public class RoomController {

	private Logger log = LoggerFactory.getLogger(RoomController.class);
	@Autowired
	private RoomService roomService;

	/**
	 * 创建房间 
	 * 
	 * @param userId   房主id
	 * @param gameType 游戏类型
	 * @param scene    场景(大厅/金币场/训练场/比赛场/俱乐部/上分俱乐部...)
	 * @param config   创房参数
	 * @return Room
	 */
	// 注：@GetMapping("/{id}")是spring 4.3的新注解
	// 等价于：@RequestMapping(value = "/id", method = RequestMethod.GET)
	// 类似的注解还有@PostMapping等等
	@GetMapping("/room")
	public Room createRoom(
			@RequestParam("userId") String userId,
			@RequestParam("gameType") Integer gameType,
			@RequestParam("scene") String scene,
			@RequestParam("config") JSONObject config) {
		return roomService.createRoom(userId,gameType,scene,config);
	}
	
	/**
	 * 解散未开始的游戏房间
	 * @param userId
	 * @return
	 */
	@DeleteMapping("/room/{userId}")
	public List<String> dissolveRoom(
			@PathVariable("userId") String userId) {
		return roomService.dissolveRoom(userId,false);
	}
	
	/**
	 * 玩家退出房间
	 * @param userId
	 * @return
	 */
	@PostMapping("/room/exit/{userId}")
	public boolean exitRoom(
			@PathVariable("userId") String userId) {
		return roomService.exitRoom(userId);
	}

	
	
	// @RequestMapping("/{id}")
	// User findByIdFeign(@PathVariable("id") Long id);

	// @RequestMapping("/time_out/{time}")
	// User testTimeOutFeign(@PathVariable("time") Long time);

	// @RequestMapping(value = "/hi",method= RequestMethod.GET)
	// User sayHi(@RequestParam("message") String message);

	// @GetMapping(value = "/his")
	// User sayHis(@RequestParam("message") String message);

}

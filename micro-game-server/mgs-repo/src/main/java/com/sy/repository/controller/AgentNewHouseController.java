package com.sy.repository.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.sy.repository.service.AgentNewHouseService;

@RestController
public class AgentNewHouseController {

	@Autowired
	AgentNewHouseService agentNewHouseService;

	@GetMapping("/getNewHouse")
	public PageInfo<Map<String, String>> getNewHouse(
			@RequestParam("page") int page,
			@RequestParam("pageSize") int pageSize) {
		return agentNewHouseService.getNewHouse(page, pageSize);
	}

	@GetMapping("/getNewHouse/{userId}")
	public List<Map<String, String>> getNewHouseByUserId(
			@PathVariable("userId") String userId) {
		return agentNewHouseService.getNewHouseByUserId(userId);
	}

	public List<Map<String, String>> getNewHouseByHouseId(String houseId) {
		return agentNewHouseService.getNewHouseByHouseId(houseId);
	}

	public PageInfo<Map<String, String>> getNewHouseByGameType(int page, int pageSize, int gameType) {
		return agentNewHouseService.getNewHouseByGameType(page, pageSize, gameType);
	}
}

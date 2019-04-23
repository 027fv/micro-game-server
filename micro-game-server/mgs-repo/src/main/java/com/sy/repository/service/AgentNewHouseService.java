package com.sy.repository.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sy.repository.mapper.AgentNewHouseMapper;

@Repository
public class AgentNewHouseService {

	@Autowired
	AgentNewHouseMapper agentNewHouseMapper;

	public PageInfo<Map<String, String>> getNewHouse(int page, int pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Map<String, String>> list = agentNewHouseMapper.getNewHouse(null, null, null);
		PageInfo<Map<String, String>> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}

	public List<Map<String, String>> getNewHouseByUserId(String userId) {
		return agentNewHouseMapper.getNewHouse(userId, null, null);
	}

	public List<Map<String, String>> getNewHouseByHouseId(String houseId) {
		return agentNewHouseMapper.getNewHouse(null, houseId, null);
	}

	public PageInfo<Map<String, String>> getNewHouseByGameType(int page, int pageSize, int gameType) {
		PageHelper.startPage(page, pageSize);
		List<Map<String, String>> list = agentNewHouseMapper.getNewHouse(null, null, gameType);
		PageInfo<Map<String, String>> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
}

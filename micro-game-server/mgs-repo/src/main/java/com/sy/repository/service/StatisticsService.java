package com.sy.repository.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sy.repository.mapper.StatisticsMapper;

@Repository
public class StatisticsService {
	
	@Autowired
	StatisticsMapper statisticsMapper;
	
	public PageInfo<Map<String, String>> getStatistics(int page, int pageSize, int gameType){
		PageHelper.startPage(page, pageSize);
		List<Map<String, String>> list = statisticsMapper.getStatistics(gameType);
		PageInfo<Map<String, String>> pageInfo = new PageInfo<>(list);
		return pageInfo;
	}
	
	public Integer setStatistics(int gameType,int consume){
		return statisticsMapper.setStatistics(gameType, consume);
	}
}

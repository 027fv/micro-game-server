package com.sy.repository.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author yy
 */
@Mapper
public interface StatisticsMapper {
	
	public List<Map<String, String>> getStatistics(@Param("gameType")int gameType);
	
	public Integer setStatistics(@Param("gameType")int gameType,
			@Param("consume")int consume);
}


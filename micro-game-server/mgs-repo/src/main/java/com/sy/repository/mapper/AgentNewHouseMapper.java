package com.sy.repository.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author yy
 */
@Mapper
public interface AgentNewHouseMapper {
	
	public List<Map<String, String>> getNewHouse(@Param("userId")String userId,@Param("houseId")String houseId,@Param("gameType")Integer gameType); 
	
}


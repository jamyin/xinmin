package com.ssic.xinmin.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface TeamExMapper {
	Map<String,Object> findOne(@Param("id") String id);
	Integer findCounts(@Param("id")String id);
}

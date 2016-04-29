package com.ssic.xinmin.service.api;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssic.xinmin.utils.dto.UserDto;

@Service
public interface IUserService {
	public int joinTeam(UserDto userDto);
	
	public Map<String,Object> findMap(String id);
}

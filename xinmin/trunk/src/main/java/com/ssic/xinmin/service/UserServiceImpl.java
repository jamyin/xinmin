package com.ssic.xinmin.service;

import java.util.Map;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssic.xinmin.dao.UserServiceDao;
import com.ssic.xinmin.service.api.IUserService;
import com.ssic.xinmin.utils.dto.UserDto;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	@Getter
	private UserServiceDao userServiceDao;
	
	//@Override
	public int joinTeam(UserDto userDto) {
		if(userDto!=null){
			
			return userServiceDao.joinGame(userDto);
		}else{
			return -1;
		}
		
	}

	@Override
	public Map<String, Object> findMap(String id) {
		return userServiceDao.findAll(id);
	}

}

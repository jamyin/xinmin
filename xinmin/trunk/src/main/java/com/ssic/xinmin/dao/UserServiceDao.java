package com.ssic.xinmin.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.ssic.xinmin.mapper.TeamExMapper;
import com.ssic.xinmin.mapper.UsersMapper;
import com.ssic.xinmin.pojo.Users;
import com.ssic.xinmin.pojo.UsersExample;
import com.ssic.xinmin.utils.BeanUtils;
import com.ssic.xinmin.utils.UUIDGenerator;
import com.ssic.xinmin.utils.constants.DataStatus;
import com.ssic.xinmin.utils.dto.UserDto;

@Repository
public class UserServiceDao {
	
	@Autowired
	@Getter
	private UsersMapper mapper;
	
	@Autowired
	@Getter
	private TeamExMapper exMapper;
	
	public int joinGame(UserDto userDto){
		Users users = new Users();
		BeanUtils.copyProperties(userDto, users);
		users.setId(UUIDGenerator.getUUID());
		users.setCreateTime(new Date());
		users.setLastUpdateTime(new Date());
		users.setStat(DataStatus.ENABLED);
		return mapper.insert(users);
	}
	public Map<String,Object> findAll(String teamId){
		
		Map<String,Object> result= new HashMap<String,Object>();
		result = exMapper.findOne(teamId);
		Integer tempCount = exMapper.findCounts(teamId);
		if(tempCount!=null){
			result.put("teamCount", tempCount+"");
		}
		
		UsersExample example = new UsersExample();
		UsersExample.Criteria criteria = example.createCriteria();
		if(!StringUtils.isEmpty(teamId)){
			criteria.andTeamIdEqualTo(teamId);
		}
		criteria.andStatEqualTo(DataStatus.ENABLED);
		example.setOrderByClause(" create_time desc");
		List<Users> tempList = mapper.selectByExample(example);
		if(tempList!=null&&tempList.size()>0){
			List<UserDto> lists = BeanUtils.createBeanListByTarget(tempList, UserDto.class);
			result.put("UserDtoList", lists);
		}
		
		return result;
	}
}

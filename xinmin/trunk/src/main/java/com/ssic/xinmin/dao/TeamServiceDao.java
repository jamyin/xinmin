package com.ssic.xinmin.dao;

import java.util.Date;
import java.util.List;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssic.xinmin.mapper.TeamMapper;
import com.ssic.xinmin.pojo.Team;
import com.ssic.xinmin.pojo.TeamExample;
import com.ssic.xinmin.pojo.TeamExample.Criteria;
import com.ssic.xinmin.utils.StringUtils;
import com.ssic.xinmin.utils.UUIDGenerator;
import com.ssic.xinmin.utils.constants.DataStatus;
import com.ssic.xinmin.utils.model.PageQuery;

@Repository
public class TeamServiceDao {
	
	@Autowired
	@Getter
	private TeamMapper mapper;

	public Integer save(Team team) {
		team.setId(UUIDGenerator.getUUID());
		team.setCreateTime(new Date());
		team.setLastUpdateTime(new Date());
		team.setStat(DataStatus.ENABLED);
		return mapper.insert(team);
	}

	public List<Team> findTeam(Team team, PageQuery page) {
		TeamExample example = new TeamExample();
		Criteria criteria = example.createCriteria();

		if(!StringUtils.isEmpty(team.getRegionId())){
			criteria.andRegionIdEqualTo(team.getRegionId());
		}
		if(!StringUtils.isEmpty(team.getTeamName())){
			criteria.andTeamNameLike("%"+team.getTeamName()+"%");   //模糊查询 (记得加百分号)
		}
		if(team.getTeamType() != null && !(StringUtils.isEmpty(team.getTeamType().toString()))){
			criteria.andTeamTypeEqualTo(team.getTeamType());
		}
		criteria.andStatEqualTo(DataStatus.ENABLED);
		example.setOrderByClause(" create_time desc limit " + page.getStartNum() + ", " + page.getPageSize());
		return mapper.selectByExample(example);
	}

	public long selectAllTeamList(Team team) {
		TeamExample example = new TeamExample();
		Criteria criteria = example.createCriteria();
		if(!StringUtils.isEmpty(team.getRegionId())){
			criteria.andRegionIdEqualTo(team.getRegionId());
		}
		if(!StringUtils.isEmpty(team.getTeamName())){
			criteria.andTeamNameLike("%"+team.getTeamName()+"%");
		}
		if(team.getTeamType() != null && !(StringUtils.isEmpty(team.getTeamType().toString()))){
			criteria.andTeamTypeEqualTo(team.getTeamType());
		}
		criteria.andStatEqualTo(DataStatus.ENABLED);
		return mapper.countByExample(example);
	}

	public List<Team> findTeamNoPage(Team team) {
		TeamExample example = new TeamExample();
		Criteria criteria = example.createCriteria();
		if(!StringUtils.isEmpty(team.getTeamName())){
			criteria.andTeamNameEqualTo(team.getTeamName());
		}
		return mapper.selectByExample(example);
	}
	
}

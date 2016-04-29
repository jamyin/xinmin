package com.ssic.xinmin.service;

import java.util.List;

import lombok.Getter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssic.xinmin.dao.TeamServiceDao;
import com.ssic.xinmin.dto.TeamDto;
import com.ssic.xinmin.pojo.Team;
import com.ssic.xinmin.service.api.ITeamService;
import com.ssic.xinmin.utils.BeanUtils;
import com.ssic.xinmin.utils.model.PageQuery;
import com.ssic.xinmin.utils.model.PageResult;


@Service
public class TeamServiceImpl implements ITeamService{

	@Autowired
	@Getter
	private TeamServiceDao teamServiceDao;

	public Integer save(TeamDto teamDto) {
		Team team = BeanUtils.createBeanByTarget(teamDto, Team.class);
		return teamServiceDao.save(team);
	}

	public PageResult<TeamDto> findTeam(TeamDto teamDto, PageQuery page) {
		Team team = BeanUtils.createBeanByTarget(teamDto, Team.class);
		List<Team> list = teamServiceDao.findTeam(team, page);
		long total = teamServiceDao.selectAllTeamList(team);
		page.setTotal(total);
		List<TeamDto> teamDto_ = BeanUtils.createBeanListByTarget(list,TeamDto.class);
		//return teamDto_;
		return new PageResult<TeamDto>(page, teamDto_);
	}

	@Override
	public List<TeamDto> findTeamNoPage(TeamDto teamDto) {
		Team team = BeanUtils.createBeanByTarget(teamDto, Team.class);
		List<Team> list = teamServiceDao.findTeamNoPage(team);
		List<TeamDto> teamDto_ = BeanUtils.createBeanListByTarget(list,TeamDto.class);
		return teamDto_;
	}

}

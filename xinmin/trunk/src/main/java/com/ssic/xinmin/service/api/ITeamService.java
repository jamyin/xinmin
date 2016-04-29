package com.ssic.xinmin.service.api;

import java.util.List;

import com.ssic.xinmin.dto.TeamDto;
import com.ssic.xinmin.utils.model.PageQuery;
import com.ssic.xinmin.utils.model.PageResult;

public interface ITeamService {
	//增加或修改球队
	public Integer save(TeamDto teamDto);
	//查询球队
	public PageResult<TeamDto> findTeam(TeamDto teamDto, PageQuery page);
	
	public List<TeamDto> findTeamNoPage(TeamDto teamDto);

}

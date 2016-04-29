package com.ssic.xinmin.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssic.xinmin.dto.TeamDto;
import com.ssic.xinmin.service.api.ITeamService;
import com.ssic.xinmin.utils.constants.DataStatus;
import com.ssic.xinmin.utils.model.PageQuery;
import com.ssic.xinmin.utils.model.PageResult;
import com.ssic.xinmin.utils.model.Response;

/**
 * 此类描述的是：球队管理controller
 * @author: jam_yin
 * @version: 2015年5月21日 上午10:27:11
 */
@Controller
@RequestMapping(value = "/team")
public class TeamController {	
	protected static final Log logger = LogFactory.getLog(TeamController.class);
	@Autowired
	private ITeamService iTeamService;

	/**
	 * 此方法描述的是：组建球队
	 * @author: jam_yin
	 * @version: 2015年5月21日 上午10:28:13
	 */
	@RequestMapping(value = "/save.do")
	@ResponseBody
	public Response<Integer> save(TeamDto teamDto) {
		Response<Integer> result = new Response<Integer>();
		
		List<TeamDto> list = iTeamService.findTeamNoPage(teamDto);
		if(list.size() > 0){
		    result.setMessage("球队名已经存在!");
		    result.setStatus(DataStatus.HTTP_FAILE);
		    return result;
	    }
		
		Integer flag = iTeamService.save(teamDto);
			if(flag > 0){
			    result.setMessage("创建球队成功!");
			    result.setStatus(DataStatus.HTTP_SUCCESS);
		    }else{
			    result.setMessage("创建球队失败!");
			    result.setStatus(DataStatus.HTTP_FAILE);
		    }
		return result;
}
	
/**
 * 此方法描述的是：检查球队名重复
 * @author: jam_yin
 * @version: 2015年5月22日 上午13:20:10
 */
@RequestMapping(value = "/checkTeamName.do")
@ResponseBody
public Response<List<TeamDto>> checkTeamName(TeamDto teamDto) {
	Response<List<TeamDto>> result = new Response<List<TeamDto>>();
	List<TeamDto> list = iTeamService.findTeamNoPage(teamDto);
		if(list.size() > 0){
		    result.setMessage("球队名已经存在!");
		    result.setStatus(DataStatus.HTTP_SUCCESS);
	    }else{
		    result.setMessage("球队名可以使用!");
		    result.setStatus(DataStatus.HTTP_FAILE);
	    }
	return result;
}

	/**
	 * 此方法描述的是：球队列表
	 * @author: jam_yin
	 * @version: 2015年5月21日 上午11:33:22
	 */
	@RequestMapping(value = "/teamList.do")
	@ResponseBody
	public Response<PageResult<TeamDto>> teamList(TeamDto teamDto,PageQuery page) {
		Response<PageResult<TeamDto>> result = new Response<PageResult<TeamDto>>();
		if(StringUtils.isEmpty(teamDto.getRegionId())||"-1".equals(teamDto.getRegionId())){
			teamDto.setRegionId(null);
		}
		if(StringUtils.isEmpty(teamDto.getTeamName())||"-1".equals(teamDto.getTeamName())){
			teamDto.setTeamName(null);
		}
		PageResult<TeamDto> list = iTeamService.findTeam(teamDto,page);			
		if(list.getTotal() > 0){
		    result.setMessage("查询球队成功!");
		    result.setData(list);
		    result.setStatus(DataStatus.HTTP_SUCCESS);
	    }else{
		    result.setMessage("没有相应的球队!");
		    result.setStatus(DataStatus.HTTP_FAILE);
	    }
	return result;
	}
	
}

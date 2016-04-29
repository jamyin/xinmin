package com.ssic.xinmin.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ssic.xinmin.service.api.IUserService;
import com.ssic.xinmin.utils.DateUtils;
import com.ssic.xinmin.utils.constants.DataStatus;
import com.ssic.xinmin.utils.dto.UserDto;
import com.ssic.xinmin.utils.model.Response;

@Controller
@RequestMapping(value = "/umanages")
public class UserController {
	protected static final Log logger = LogFactory.getLog(UserController.class);

	@Autowired
	private IUserService iUserService;
	
	
	@RequestMapping(value="/join.do")
	@ResponseBody
	public Response<String> join(UserDto userDto,HttpSession session){
		Response<String> result = new Response<String>();
		
		//先写死
//		userDto.setTeamId("100f9f45-6ff8-4259-a1bc-70599f75f85a");
		
		if(session.getAttribute(DataStatus.STADID)!=null){
			userDto.setTeamId(session.getAttribute(DataStatus.STADID)+"");
			session.removeAttribute(DataStatus.STADID);
		}else{
			result.setStatus(DataStatus.HTTP_FAILE);
			result.setMessage("加入队伍失败，原因为取不到队伍ID");
			return result;
		}
		
		if(!StringUtils.isEmpty(userDto.getTempBirthday())){
			Date tempDate = DateUtils.parse(userDto.getTempBirthday(), DateUtils.YMD_DASH);
			userDto.setBrithday(tempDate);
		}
		int results = iUserService.joinTeam(userDto);
		if(results!=1){
			result.setStatus(DataStatus.HTTP_FAILE);
			result.setMessage("加入失败!");
			return result;
		}else{
			result.setStatus(DataStatus.HTTP_SUCCESS);
			result.setMessage("加入成功!");
			result.setData(userDto.getTeamId());
			return result;
		}
	}
	@RequestMapping(value="/jump.do")
	@ResponseBody
	public Response<String> jump(String id,HttpSession session){
		Response<String> result = new Response<String>();
		if(!StringUtils.isEmpty(id)){
			session.setAttribute(DataStatus.STADID, id);
			return result;
			
		}else{
			result.setStatus(DataStatus.HTTP_FAILE);
			return result;
		}
	
	}
	
	@RequestMapping(value="/findAll.do")
	@ResponseBody
	public Response<Map<String,Object>> findAll(String id){
		Response<Map<String,Object>> result = new Response<Map<String,Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		map = iUserService.findMap(id);
		result.setStatus(DataStatus.HTTP_SUCCESS);
		result.setData(map);
		return result;
	}

}

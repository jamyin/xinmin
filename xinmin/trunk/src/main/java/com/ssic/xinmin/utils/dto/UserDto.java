package com.ssic.xinmin.utils.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class UserDto {

	@Getter
	@Setter
	private String id;
	
	@Getter
	@Setter
	private String realName;
	
	@Getter
	@Setter
	private String identifyId;
	
	@Getter
	@Setter
	private Date brithday;
	
	@Getter
	@Setter
	private String tempBirthday;
	
	@Getter
	@Setter
	private String mobileNo;
	
	@Getter
	@Setter
	private String userImage;
	
	@Getter
	@Setter
	private String guardName;
	
	@Getter
	@Setter
	private String guardMobile;
	
	@Getter
	@Setter
	private String orgin;
	
	@Getter
	@Setter
	private String nation;
	
	@Getter
	@Setter
	private String height;
	
	@Getter
	@Setter
	private String weight;
	
	@Getter
	@Setter
	private String pos;
	
	@Getter
	@Setter
	private Date createTime;
	
	@Getter
	@Setter
	private Date lastUpdateTime;
	
	
	@Getter
	@Setter
	private Integer stat;
	
	@Getter
	@Setter
	private String teamId;
	
	@Getter
	@Setter
	private String studentCard;
	
}

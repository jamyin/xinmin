package com.ssic.xinmin.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class TeamDto {
	@Getter
	@Setter
    private String id;
	
	@Getter
	@Setter
    private String teamName;
	
	@Getter
	@Setter
    private String regionId;
	
	@Getter
	@Setter
    private String schoolName;
	
	@Getter
	@Setter
    private Integer teamType;
	
	@Getter
	@Setter
    private String captainName;
	
	@Getter
	@Setter
    private String captainPhone;
	
	@Getter
	@Setter
    private String teamDesc;
	
	@Getter
	@Setter
    private String teamLogo;
	
	@Getter
	@Setter
    private Date createTime;
	
	@Getter
	@Setter
    private Date lastUpdateTime;
	
	@Getter
	@Setter
    private Integer stat;
   
}
package com.ssic.xinmin.utils.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class DateDto {
	@Getter
	@Setter
	private String day;
	
	@Getter
	@Setter
	private String week;
	
	@Getter
	@Setter
	private String date;
	
	@Getter
	@Setter
	private String nowDay;
	
	@Getter
	@Setter
	private Short dayForWeek;
}

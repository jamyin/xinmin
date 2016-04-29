package com.ssic.xinmin.cache;
import java.util.List;

import com.ssic.xinmin.dto.AddressesDto;



public interface IAddressesCache {

	public AddressesDto get(Integer key);
	
	public String findNameById(Integer key);
	
	public List<AddressesDto> findByParentId(String parentId);
}

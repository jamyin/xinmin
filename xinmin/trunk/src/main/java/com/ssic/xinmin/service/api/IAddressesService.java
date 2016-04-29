package com.ssic.xinmin.service.api;


import java.util.List;

import org.springframework.stereotype.Service;

import com.ssic.xinmin.dto.AddressesDto;
import com.ssic.xinmin.utils.model.ListResult;

@Service
public interface IAddressesService {
	
	public List<AddressesDto> findBy(AddressesDto addressesDto);

	public ListResult<AddressesDto> findByParentId(Integer parentId);
}

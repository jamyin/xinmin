package com.ssic.xinmin.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssic.xinmin.cache.IAddressesCache;
import com.ssic.xinmin.dao.AddressesDao;
import com.ssic.xinmin.dto.AddressesDto;
import com.ssic.xinmin.pojo.Addresses;
import com.ssic.xinmin.service.api.IAddressesService;
import com.ssic.xinmin.utils.BeanUtils;
import com.ssic.xinmin.utils.model.ListResult;

@Service
public class AddressesServiceImpl implements IAddressesService {

	@Autowired
	private AddressesDao addressesDao;
	
	@Autowired
	private IAddressesCache addressesCache;
	
	@Override
	public List<AddressesDto> findBy(AddressesDto addressesDto) {
		List<Addresses> list = addressesDao.findBy(addressesDto);
		List<AddressesDto> results = BeanUtils.createBeanListByTarget(list, AddressesDto.class);
		return results;
	}

	@Override
	public ListResult<AddressesDto> findByParentId(Integer parentId) {
		List<AddressesDto> lists = addressesCache.findByParentId(parentId.toString());
		return new ListResult<AddressesDto>(lists);
	}

}

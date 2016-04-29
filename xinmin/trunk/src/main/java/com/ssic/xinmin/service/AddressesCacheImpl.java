package com.ssic.xinmin.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.ssic.xinmin.cache.IAddressesCache;
import com.ssic.xinmin.dao.AddressesDao;
import com.ssic.xinmin.dto.AddressesDto;
import com.ssic.xinmin.pojo.Addresses;
import com.ssic.xinmin.utils.BeanUtils;

@Service
public class AddressesCacheImpl implements IAddressesCache{
	
	@Autowired
	private AddressesDao addressesDao;
	
	//id查询缓存
	private LoadingCache<Integer, AddressesDto> areas = CacheBuilder.newBuilder().build(
			
			new CacheLoader<Integer, AddressesDto>() {										
					
					public AddressesDto load(Integer key){
					       return createArea(key);
					}

					private AddressesDto createArea(Integer key) {
						if(key == null) {
							return null;
						}
						Addresses address = addressesDao.findById(key);
						return address != null ? (AddressesDto)BeanUtils.createBeanByTarget(address, AddressesDto.class) : null;
					}
				}
			);
	
	//parentId查询缓存
	private LoadingCache<String, List<AddressesDto>> areasList = CacheBuilder.newBuilder().build(
			
			new CacheLoader<String, List<AddressesDto>>() {										
					
					public List<AddressesDto> load(String key){
					       return createAreaList(key);
					}

					private List<AddressesDto> createAreaList(String key) {
						List<Addresses> addressList = addressesDao.findByParentId(key);
						return BeanUtils.createBeanListByTarget(addressList, AddressesDto.class);
					}
				}
			);
	
	public AddressesDto get(Integer key){
		return areas.getUnchecked(key);
	}
	
	public String findNameById(Integer key) {
		if(key == null) {
			return null;
		}
		try {
			AddressesDto areaDto = areas.getUnchecked(key);
			return areaDto != null ? areaDto.getName() : null;
		} catch (Exception e) {
			return null;
		}
	}
	
	public List<AddressesDto> findByParentId(String parentId) {
		List<AddressesDto> list = areasList.getUnchecked(parentId);
		return list;
	}
}

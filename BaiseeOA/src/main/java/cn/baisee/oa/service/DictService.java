package cn.baisee.oa.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.baisee.oa.model.Area;
import cn.baisee.oa.model.City;
import cn.baisee.oa.model.IcipBusDict;
import cn.baisee.oa.model.Province;
import cn.baisee.oa.page.pagehelper.PageInfo;


@Service
public interface DictService {
	public PageInfo<IcipBusDict> getDictList(int pageNum,int pageSize);
	
	public Integer delDict(String[] dictIds);
	
	public IcipBusDict getDictById(String dictId);
	
	public Integer updateDict(IcipBusDict dict);
	
	public IcipBusDict  checkDict(IcipBusDict dict);
	/**
	 * 获取省份信息
	 * @return
	 */
	List<Province> getProvince();
	/**
	 * 获取城市信息
	 * @param provinceCode
	 * @return
	 */
	List<City> getCity(String provinceCode);
	/**
	 * 获取区县信息
	 * @param cityCode
	 * @return
	 */
	List<Area> getArea(String cityCode);
	
}

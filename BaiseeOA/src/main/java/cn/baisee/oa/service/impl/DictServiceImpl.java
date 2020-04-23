package cn.baisee.oa.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.baisee.BaiseeDictMapper;
import cn.baisee.oa.model.Area;
import cn.baisee.oa.model.City;
import cn.baisee.oa.model.IcipBusDict;
import cn.baisee.oa.model.Province;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.DictService;

@Service
public class DictServiceImpl implements DictService {
	@Autowired
	private BaiseeDictMapper dictMapper;
	
	@Override
	public PageInfo<IcipBusDict> getDictList(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<IcipBusDict> list=dictMapper.getAllDict(new HashMap<String, Object>());
		PageInfo<IcipBusDict> page=new PageInfo<IcipBusDict>(list);
		return page;
	}

	@Override
	public Integer delDict(String[] dictIds) {
		return dictMapper.delDict(dictIds);
	}

	@Override
	public IcipBusDict getDictById(String dictId) {
		return dictMapper.getDictById(dictId);
	}

	@Override
	public Integer updateDict(IcipBusDict dict) {
		return dictMapper.updateDict(dict);
	}

	@Override
	public IcipBusDict checkDict(IcipBusDict dict) {
		if (dictMapper.checkDict(dict)!=null) {
			return dictMapper.checkDict(dict);
		}
		return null;
	}

	@Override
	public List<Province> getProvince() {
		return dictMapper.selectProvince();
	}

	@Override
	public List<City> getCity(String provinceCode) {
		return dictMapper.selectCity(provinceCode);
	}

	@Override
	public List<Area> getArea(String cityCode) {
		return dictMapper.selectArea(cityCode);
	}

}

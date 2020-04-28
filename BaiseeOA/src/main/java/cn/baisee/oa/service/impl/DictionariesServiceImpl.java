package cn.baisee.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.baisee.BaiseeDicMapper;
import cn.baisee.oa.model.IcipBusDict;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.DictionariesService;

@Service
public class DictionariesServiceImpl implements DictionariesService {

    @Autowired
    private BaiseeDicMapper baiseeDicMapper;

    public void insertDic(IcipBusDict dic) throws Exception {
        baiseeDicMapper.insertDic(dic);

    }

    @Override
    public PageInfo<IcipBusDict> selectAllDic(int pageNum, int pageSize, String itemlableSearch) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("itemlableSearch", itemlableSearch);
        List<IcipBusDict> list = baiseeDicMapper.selectAllDic(map);
        PageInfo<IcipBusDict> page = new PageInfo<IcipBusDict>(list);
        return page;
    }

    @Override
    public IcipBusDict selectDicById(String dictId) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dictId", dictId);
        return baiseeDicMapper.selectDicById(map);
    }

    @Override
    public void updateDicById(IcipBusDict dic) throws Exception {
        baiseeDicMapper.updateDicById(dic);

    }

    @Override
    public IcipBusDict selectDicByValue(String dictValue) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dictValue", dictValue);
        return baiseeDicMapper.selectDicByValue(map);
    }

    @Override
    public Integer deleteDic(String[] dictIds) throws Exception {
        return baiseeDicMapper.deleteDic(dictIds);
    }

    @Override
    public List<IcipBusDict> selectIcipBusDictByDictName(String name) {
        return baiseeDicMapper.selectIcipBusDictByDictName(name);
    }

    @Override
    public PageInfo<IcipBusDict> getDepartments(int pageNum, int pageSize, String itemlableSearch) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("itemlableSearch", itemlableSearch);
        List<IcipBusDict> list = baiseeDicMapper.getDepartments(map);
        PageInfo<IcipBusDict> page = new PageInfo<IcipBusDict>(list);
        return page;
    }

    @Override
    public List<IcipBusDict> checkDeptName(String dictDestination) {
        return baiseeDicMapper.checkDeptName(dictDestination);
    }

    @Override
    public void update(IcipBusDict dic) {
        String id = dic.getDictId();
        id = id.replaceAll(",", "");
        dic.setDictId(id);
        baiseeDicMapper.update(dic);
    }

    @Override
    public PageInfo<IcipBusDict> getRoles(int pageNum, int pageSize, String itemlableSearch) {
        PageHelper.startPage(pageNum, pageSize);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("itemlableSearch", itemlableSearch);
        List<IcipBusDict> list = baiseeDicMapper.getRoles(map);
        PageInfo<IcipBusDict> page = new PageInfo<IcipBusDict>(list);
        return page;
    }

    @Override
    public List<IcipBusDict> getDepartmentList() {
        return baiseeDicMapper.getDepartmentList();
    }

    @Override
    public List<IcipBusDict> getRoleByDId(String dId) {
        return baiseeDicMapper.getRoleByDId(dId);
    }

    @Override
    public void insertDept(IcipBusDict dic) throws Exception {
        baiseeDicMapper.insertDept(dic);
    }

    @Override
    public List<IcipBusDict> getDpetValue(String dValue) {
        return baiseeDicMapper.getDpetValue(dValue);
    }

    @Override
    public PageInfo<IcipBusDict> findPageByDictName(int pageNum, int pageSize, String dictName, String itemlableSearch)
            throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("dictName", dictName);
        map.put("itemlableSearch", itemlableSearch);
        List<IcipBusDict> list = baiseeDicMapper.selectListByMap(map);
        PageInfo<IcipBusDict> page = new PageInfo<IcipBusDict>(list);
        return page;
    }


}

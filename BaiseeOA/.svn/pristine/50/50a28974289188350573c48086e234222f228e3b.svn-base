package cn.baisee.oa.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


















import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.baisee.BaiseeClazzMapper;
import cn.baisee.oa.dao.baisee.BaiseeStuMapper;
import cn.baisee.oa.dao.baiseew.BaiseeEvaluateInfoMapper;
import cn.baisee.oa.model.BaiseeClazz;
import cn.baisee.oa.model.BaiseeEvaluateInfo;
import cn.baisee.oa.model.student.BaiseeStudent;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeEvaluateInfoService;

@Service
public class BaiseeEvaluateInfoServiceImpl implements BaiseeEvaluateInfoService {

	@Autowired
	private BaiseeEvaluateInfoMapper mapper;
	
	@Autowired
	private BaiseeStuMapper baiseeStuMapper;
	
	@Autowired
	private BaiseeClazzMapper clazzMapper;
	/**
	 * 这个是列表查询
	 */
	@Override
	public PageInfo<BaiseeEvaluateInfo> selectAll(int pageNum,int pageSize,String itemlableSearch,String clazz) {
		//---------------------------------------都是查询时候的条件
		/**
		 * 查询分为两部分，第一部分是查询班级，第二是姓名查询
		 * 先声明一个map，所有的查询参数都是map放入的
		 * 第一：查询班级，从前台页面传递clazz（clazz为查询的参数），经过StringUtils的不为空判断
		 * 把获取到的cid（班级的id）使用map.put放入到map集合中。
		 * 
		 * 第二：查询姓名，前台页面传递itemlableSearch（姓名），直接把它放入map集合中。
		 */
		Map<String, Object> map = new HashMap<String, Object>();
		String cid = ""; 
		if(StringUtils.isNotEmpty(clazz)){
			List<BaiseeClazz> clazzs = clazzMapper.selectCID(clazz);
			for (BaiseeClazz baiseeClazz : clazzs) {
				String cids = baiseeClazz.getcId();
				cid+=cids;
			}
			map.put("cid", cid);
			/*if (cid!=null) {
				cid=cid.substring(0,cid.length()-1);
			}*/
		}
		/*String[] cids = cid.split(",");
		if (cids!=null && cids.length>0) {
			if (cids.length==1) {
				map.put("cid", cids[0]);
			}
		}*/
		/**
		 * 获取数据
		 */
		String ids = "";
		map.put("itemlableSearch", itemlableSearch);
		List<BaiseeStudent> student = baiseeStuMapper.selectNameById(map);
		for (BaiseeStudent baiseeStudent : student) {
			String sid = baiseeStudent.getStuId();
			ids+=sid+",";
		}
		if (ids!=null && ids!="") {
			ids=ids.substring(0,ids.length()-1);
		}
		//---------------------------------------到----------------------
			PageHelper.startPage(pageNum, pageSize);	
			List<BaiseeEvaluateInfo> list = mapper.selectAll(ids);
			if(list!=null && list.size()>0){
	 			for(int i=0;i<list.size();i++){
	 				Map<String, Object> m=new HashMap<String, Object>();
					m.put("sid", list.get(i).getsId());
					List<BaiseeStudent> stus = baiseeStuMapper.selectAll(m);
					BaiseeClazz clazzName = clazzMapper.selectCNAME(stus.get(0).getClaId());
					list.get(i).setName(stus.get(0).getStuName());
					list.get(i).setClazz(clazzName.getcName());
				}
	 		}
			/*Collections.sort(list,new BaiseeEvaluateInfo());
			List<String> lit = new ArrayList<String>();
			String sid = "";
			for (BaiseeEvaluateInfo baiseeEvaluateInfo : list) {
				sid = baiseeEvaluateInfo.getSid();
				lit.add(sid);
			}
	 		
	 		List<BaiseeEvaluateInfo> li = mapper.selectAllId(lit);
	 		if(li!=null && li.size()>0){
	 			for(int i=0;i<li.size();i++){
	 				Map<String, Object> m=new HashMap<String, Object>();
					m.put("sid", li.get(i).getsId());
					List<BaiseeStudent> stus = baiseeStuMapper.selectAll(m);
					BaiseeClazz clazzName = clazzMapper.selectCNAME(stus.get(0).getClaId());
					li.get(i).setName(stus.get(0).getStuName());
					li.get(i).setClazz(clazzName.getcName());
				}
	 		}*/
	 		//Collections.sort(li,new BaiseeEvaluateInfo());
			PageInfo<BaiseeEvaluateInfo> page=new PageInfo<BaiseeEvaluateInfo>(list);
			return page;
		
	}
	
	
	/**
	 * 这个是添加方法
	 */
	@Override
	public void insertDate(BaiseeEvaluateInfo insert) {
		
		mapper.insertDate(insert);
	}

	

	/**
	 * 根据id查询一条数据/修改用
	 */
	@Override
	public BaiseeEvaluateInfo selectById(int evid) {
		BaiseeEvaluateInfo info = mapper.selectById(evid);
		BaiseeStudent stu = baiseeStuMapper.selectById(info.getsId());
		info.setName(stu.getStuName());
		return info;
	}


	/**
	 * 修改/BaiseeEvaluateInfo
	 */
	@Override
	public void updateEVinfo(BaiseeEvaluateInfo info) {
		mapper.updateEVinfo(info);
	}


	

	/**
	 * 根据evid删除一条数据
	 */
	@Override
	public void deleteEva(int[] evid) {
		mapper.deleteEva(evid);
	}



	/**
	 * 这是查询到所有的班级,并把班级放入到评论类里,	用于显示班级下拉框
	 */
	@Override
	public BaiseeEvaluateInfo Cname() {
		BaiseeEvaluateInfo info = new BaiseeEvaluateInfo();
		List<BaiseeClazz> list = clazzMapper.selectCnames();
		info.setClazzs(list);
		return info;
	}
	/**
	 * 获取到班级名称
	 * @param sid :根据前台传递的sid查询到CNAME
	 * @return
	 */
	public String getCname(String sid){
		BaiseeStudent student = baiseeStuMapper.selectById(sid);
		String cid = student.getClaId();
		BaiseeClazz cname = clazzMapper.selectCNAME(cid);
		String cName = cname.getcName();
		return cName;
	}
	
	
	
	
}

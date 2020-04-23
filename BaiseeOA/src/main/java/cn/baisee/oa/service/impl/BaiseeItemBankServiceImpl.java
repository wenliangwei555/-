package cn.baisee.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.constants.CacheKeyEnum;
import cn.baisee.oa.dao.baisee.BaiseeCourseMapper;
import cn.baisee.oa.dao.baisee.BaiseeUserMapper;
import cn.baisee.oa.dao.stu.BaiseeItemBankMapper;
import cn.baisee.oa.model.BaiseeUser;
import cn.baisee.oa.model.ReturnInfo;
import cn.baisee.oa.model.course.BaiseeCourse;
import cn.baisee.oa.model.examination.BaiseeItemBank;
import cn.baisee.oa.model.examination.BaiseeItembankTestpaper;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeItemBankService;
import cn.baisee.oa.utils.RedisUtils;

@Service
public class BaiseeItemBankServiceImpl implements BaiseeItemBankService {

	@Autowired
	private BaiseeItemBankMapper itemBankMapper;
	@Autowired
	private BaiseeUserMapper userMapper;
	@Autowired
	private BaiseeCourseMapper courseMapper;

	@Override
	public int getCheckCountItemBankById(List<String> iId) {
		return itemBankMapper.getCheckCountItemBankById(iId);
	}
	
	@Override
	public List<BaiseeItemBank> getCheckitemBankById( List<String> iId){
		return itemBankMapper.getCheckitemBankById(iId);
	}
	
	@Override
	public PageInfo<BaiseeItemBank> selectitemBankList(Map<String, Object> maps) {
		PageHelper.startPage(Integer.valueOf(maps.get("pageNum").toString()), Integer.valueOf(maps.get("pageSize").toString()));
		// 为什么不直接连接表查出信息（不在同一个库中）
		List<BaiseeItemBank> itemBankList = itemBankMapper.selectitemBankList(maps);// 查询题库信息
		BaiseeUser user = null;
		BaiseeCourse selectCourseById = null;
		for (BaiseeItemBank baiseeItemBank : itemBankList) {// 遍历题库信息 
			selectCourseById = courseMapper.selectCourseById(baiseeItemBank.getDid());//询考题类别名称
			if(selectCourseById == null) { continue; }	// 添加到属性中
			if(!"".equals(selectCourseById.getCourseTtitle()) && selectCourseById.getCourseTtitle() != null) {
				baiseeItemBank.setDictValue(selectCourseById.getCourseTtitle());
			}
			if(!"".equals(baiseeItemBank.getCreateName()) && baiseeItemBank.getCreateName() != null) {// 根据用户Id 查询用户名称
				user = userMapper.selectByPrimaryKey(baiseeItemBank.getCreateName());
				if(user!=null) {// 添加到属性当中
					if(!"".equals(user.getUserName()) && user.getUserName() != null) { 
						baiseeItemBank.setCreateName(user.getUserName());
						}
				}
				}
		}
		PageInfo<BaiseeItemBank> page = new PageInfo<BaiseeItemBank>(itemBankList);
		return page;
	}

	@Override
	public BaiseeItemBank getitemBankById(String iId) {
		return itemBankMapper.getitemBankById( iId);
	}
	@Override
	public int saveitemBank(BaiseeItemBank itemBank) {
		try {
			itemBank.setiId(userMapper.createId("IBID"));
			itemBankMapper.saveitemBank( itemBank);
			// 新增试题的时候添加缓存
			RedisUtils.utils.del(CacheKeyEnum.CURRICULUM.getCacheKey()+itemBank.getDid());// 删除Redis
			List<BaiseeItemBank> list = itemBankMapper.selectTypeQuery(itemBank.getDid());
			BaiseeUser user = null;
			BaiseeCourse selectCourseById = null;
			for (BaiseeItemBank baiseeItemBank : list) {
				user = userMapper.selectByPrimaryKey(baiseeItemBank.getCreateName());
				baiseeItemBank.setCreateName(user.getUserName());
				String str = baiseeItemBank.getCreateTs().substring(0, 8);
				StringBuilder sbd=new StringBuilder(str);
				StringBuilder insert = sbd.insert(4, "-").insert(7,"-");
				String success = insert.toString();
				baiseeItemBank.setCreateTs(success);
				selectCourseById = courseMapper.selectCourseById(baiseeItemBank.getDid());
				baiseeItemBank.setDid(selectCourseById.getCourseTtitle());
			}
			RedisUtils.utils.set(CacheKeyEnum.CURRICULUM.getCacheKey()+itemBank.getDid(), list, null);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	

	@Override
	public int updateItemBank(BaiseeItemBank itemBank) {
		try {
			itemBankMapper.updateItemBank( itemBank);
			RedisUtils.utils.del(CacheKeyEnum.CURRICULUM.getCacheKey()+itemBank.getDid());// 删除Redis
			List<BaiseeItemBank> list = itemBankMapper.selectTypeQuery(itemBank.getDid());
			BaiseeUser user = null;
			BaiseeCourse selectCourseById = null;
			for (BaiseeItemBank baiseeItemBank : list) {
				user = userMapper.selectByPrimaryKey(baiseeItemBank.getCreateName());
				baiseeItemBank.setCreateName(user.getUserName());
				String str = baiseeItemBank.getCreateTs().substring(0, 8);
				StringBuilder sbd=new StringBuilder(str);
				StringBuilder insert = sbd.insert(4, "-").insert(7,"-");
				String success = insert.toString();
				baiseeItemBank.setCreateTs(success);
				selectCourseById = courseMapper.selectCourseById(baiseeItemBank.getDid());
				baiseeItemBank.setDid(selectCourseById.getCourseTtitle());
			}
			RedisUtils.utils.set(CacheKeyEnum.CURRICULUM.getCacheKey()+itemBank.getDid(), list, null);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public boolean getitemBankBySubject1(String subject, String iId) {
		// 判断Id是否是空  如果是空就是新增校验
		int success = 0;
		try {
			if(StringUtils.isEmpty(iId)) {
				success = itemBankMapper.getitemBankBySubject( subject);
			}else {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("subject", subject);
				map.put("iId", iId);
				success = itemBankMapper.getitemBankBySubject1( map);
			}
			return success > 0 ? false :true ;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public ReturnInfo delitemBankById(String[] ids) {
		ReturnInfo info = new ReturnInfo();
		Integer siz = ids.length;
		List<BaiseeItembankTestpaper> itembankTestpaper = null;
		for (int i = 0; i < ids.length; i++) {// 判断试题是否关联试卷  如果关联则不能删除
			itembankTestpaper = itemBankMapper.selectItembankTestpaper( ids[i]);
			if(CollectionUtils.isNotEmpty(itembankTestpaper)) {
				info.setMessage("试题正在被使用,不能删除!");
				info.setCode(0);// 0否1是
				return info;
			}
		}
		List<BaiseeItemBank> list = null;
		BaiseeUser user = null;
		BaiseeCourse selectCourseById = null;
		BaiseeItemBank baiseeItemBanks = null;
		if(siz == 1) {
			// 查询
			baiseeItemBanks = itemBankMapper.getitemBankById(ids[0]);
			itemBankMapper.delitemBankById( ids[0]);
			RedisUtils.utils.del(CacheKeyEnum.CURRICULUM.getCacheKey()+baiseeItemBanks.getDid());// 删除Redis
			list = itemBankMapper.selectTypeQuery(baiseeItemBanks.getDid());
			for (BaiseeItemBank baiseeItemBank : list) {
				user = userMapper.selectByPrimaryKey(baiseeItemBank.getCreateName());
				baiseeItemBank.setCreateName(user.getUserName());
				String str = baiseeItemBank.getCreateTs().substring(0, 8);
				StringBuilder sbd=new StringBuilder(str);
				StringBuilder insert = sbd.insert(4, "-").insert(7,"-");
				String success = insert.toString();
				baiseeItemBank.setCreateTs(success);
				selectCourseById = courseMapper.selectCourseById(baiseeItemBank.getDid());
				baiseeItemBank.setDid(selectCourseById.getCourseTtitle());
			}
			RedisUtils.utils.set(CacheKeyEnum.CURRICULUM.getCacheKey()+baiseeItemBanks.getDid(), list, null);
		}else {
			for (int i = 0; i < ids.length; i++) {
				baiseeItemBanks = itemBankMapper.getitemBankById(ids[i]);
				itemBankMapper.delitemBankById( baiseeItemBanks.getiId());
				RedisUtils.utils.del(CacheKeyEnum.CURRICULUM.getCacheKey()+baiseeItemBanks.getDid());// 删除Redis
				list = itemBankMapper.selectTypeQuery( baiseeItemBanks.getDid());
				for (BaiseeItemBank baiseeItemBank : list) {
					user = userMapper.selectByPrimaryKey(baiseeItemBank.getCreateName());
					baiseeItemBank.setCreateName(user.getUserName());
					String str = baiseeItemBank.getCreateTs().substring(0, 8);
					StringBuilder sbd=new StringBuilder(str);
					StringBuilder insert = sbd.insert(4, "-").insert(7,"-");
					String success = insert.toString();
					baiseeItemBank.setCreateTs(success);
					selectCourseById = courseMapper.selectCourseById(baiseeItemBank.getDid());
					baiseeItemBank.setDid(selectCourseById.getCourseTtitle());
				}
				
				RedisUtils.utils.set(CacheKeyEnum.CURRICULUM.getCacheKey()+baiseeItemBanks.getDid(), list, null);
			}
		}
		info.setMessage("删除成功!");
		info.setCode(1);// 0否1是
		return info;
	}

	@Override
	public PageInfo<BaiseeItemBank>  selectItemBankListById(String tid, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		List<BaiseeItemBank> list = itemBankMapper.selectItemBankListById( tid);
		PageInfo<BaiseeItemBank> pageResult = new PageInfo<BaiseeItemBank>( list);
		return pageResult;
	}

	@Override
	public List<BaiseeItemBank> selectTypeQuery(String did) {
		List<BaiseeItemBank> list = itemBankMapper.selectTypeQuery( did);
		BaiseeUser user = null;
		for (BaiseeItemBank baiseeItemBank : list) {
			user = userMapper.selectByPrimaryKey(baiseeItemBank.getCreateName());// 根据用户Id 查询用户名称
			baiseeItemBank.setCreateName(user.getUserName());
		}
		return list;
	}
	
	@Override
	public List<BaiseeItemBank> selectTypeQuery1(String did, List<String> iIds) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("did", did);
		for (int i = 0; i < iIds.size(); i++) {
			map.put("iIds", iIds.get(i));
		}
		return itemBankMapper.selectTypeQuery1(map);
	}

	@Override
	public List<BaiseeItemBank> selectItem() {
		List<BaiseeItemBank> itemBankList = itemBankMapper.selectitemBankList(null);
		BaiseeUser user = null;
		BaiseeCourse selectCourseById = null;
		for (BaiseeItemBank baiseeItemBank : itemBankList) {
			if(StringUtils.isEmpty(baiseeItemBank.getDid()))
				continue;
			selectCourseById = courseMapper.selectCourseById(baiseeItemBank.getDid());// 根据字典Id 查询考题类别名称
			user = userMapper.selectByPrimaryKey(baiseeItemBank.getCreateName());
			baiseeItemBank.setCreateName(user.getUserName());// 添加到属性中
			baiseeItemBank.setDictValue(selectCourseById.getCourseTtitle());
		}
		return itemBankList;
	}

	@Override
	public boolean whetherToOccupyOrNot(String iId) {
		 try {
			List<BaiseeItembankTestpaper> selectItembankTestpaper = itemBankMapper.selectItembankTestpaper( iId);
			return CollectionUtils.isEmpty(selectItembankTestpaper) ? true : false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}

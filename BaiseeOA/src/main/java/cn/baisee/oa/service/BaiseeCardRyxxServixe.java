package cn.baisee.oa.service;

import java.util.List;

import cn.baisee.oa.model.BaiseeCardRyxx;
import cn.baisee.oa.page.pagehelper.PageInfo;

public interface BaiseeCardRyxxServixe {


	PageInfo<BaiseeCardRyxx> getCardAll(int pageNum, int pageSize,String name);
	
}

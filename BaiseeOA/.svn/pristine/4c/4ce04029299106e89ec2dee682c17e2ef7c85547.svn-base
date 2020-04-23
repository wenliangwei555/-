package cn.baisee.oa.service;

import java.util.List;

import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.model.message.BaiseeMessageTemplate;
import cn.baisee.oa.page.pagehelper.PageInfo;

/**
 * 消息
 */
public interface BaiseeMessageTemplateService {

	public BaiseeMessageTemplate findById(Long id);
	
	public void deleteById(Long id);
	
	public List<BaiseeMessageTemplate> findlist(BaiseeMessageTemplate baiseeMessageTemplate);
	
	public void insert(BaiseeMessageTemplate baiseeMessageTemplate);
	
	public int updateById(BaiseeMessageTemplate baiseeMessageTemplate);

	PageInfo<BaiseeMessageTemplate> findPage(int pageNum, int pageSize,
			BaiseeMessageTemplate baiseeMessageTemplate) throws OAServiceException;
	
	
	public void send(BaiseeMessageTemplate baiseeMessageTemplate);
}

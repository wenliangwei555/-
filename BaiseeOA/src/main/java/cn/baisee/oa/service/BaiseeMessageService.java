package cn.baisee.oa.service;

import java.util.List;

import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.model.message.BaiseeMessage;
import cn.baisee.oa.page.pagehelper.PageInfo;

/**
 * 消息
 */
public interface BaiseeMessageService {

	public List<BaiseeMessage> findlist(BaiseeMessage baiseeMessage);
	
	public Long insert(BaiseeMessage baiseeMessage);
	
	public int updateById(BaiseeMessage baiseeMessage);
	
	public int deleteById(BaiseeMessage message);
	
	public BaiseeMessage findById(Long id);
	
	public PageInfo<BaiseeMessage> findPage(int pageNum, int pageSize,
			BaiseeMessage baiseeMessage) throws OAServiceException;

	
}

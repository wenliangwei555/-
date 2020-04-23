package cn.baisee.oa.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.baisee.BaiseeMessageMapper;
import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.model.message.BaiseeMessage;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeMessageService;
/**
 * 消息
 */
@Service
public class BaiseeMessageServiceImpl implements BaiseeMessageService {

	@Autowired
	private BaiseeMessageMapper baiseeMessageMapper ;

	@Override
	public List<BaiseeMessage> findlist(BaiseeMessage baiseeMessage) {
		return baiseeMessageMapper.selectlist(baiseeMessage);
	}

	@Override
	public Long insert(BaiseeMessage baiseeMessage) {
		return baiseeMessageMapper.insert(baiseeMessage);
	}

	@Override
	public int updateById(BaiseeMessage baiseeMessage) {
		return baiseeMessageMapper.updateById(baiseeMessage);
	}
	@Override
	public int deleteById(BaiseeMessage baiseeMessage) {
		return baiseeMessageMapper.deleteById(baiseeMessage);
		
	}

	@Override
	public PageInfo<BaiseeMessage> findPage(int pageNum, int pageSize, BaiseeMessage baiseeMessage)
			throws OAServiceException {
		PageHelper.startPage(pageNum, pageSize);// 开始分页
		List<BaiseeMessage> list = baiseeMessageMapper.selectlist(baiseeMessage);
		List<BaiseeMessage> list2 =new ArrayList<>();
	
		// 0  1  2     3
        return new PageInfo<BaiseeMessage>(list);
	}

	@Override
	public BaiseeMessage findById(Long id) {
		return baiseeMessageMapper.selectById(id);
	}

	
	
	
}

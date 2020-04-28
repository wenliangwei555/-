package cn.baisee.oa.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.baisee.BaiseeMessageTemplateMapper;
import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.model.message.BaiseeMessage;
import cn.baisee.oa.model.message.BaiseeMessageTemplate;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeMessageService;
import cn.baisee.oa.service.BaiseeMessageTemplateService;
/**
 * 消息
 */
@Service
public class BaiseeMessageTemplateServiceImpl implements BaiseeMessageTemplateService {

	@Autowired
	private BaiseeMessageTemplateMapper baiseeMessageTemplateMapper ;
	@Autowired
	private BaiseeMessageService baiseeMessageService;
	
	@Override
	public List<BaiseeMessageTemplate> findlist(BaiseeMessageTemplate baiseeMessageTemplate) {
		return baiseeMessageTemplateMapper.selectlist(baiseeMessageTemplate);
	}

	@Override
	public void insert(BaiseeMessageTemplate baiseeMessageTemplate) {
		baiseeMessageTemplateMapper.insert(baiseeMessageTemplate);
	}

	@Override
	public int updateById(BaiseeMessageTemplate baiseeMessageTemplate) {
		return baiseeMessageTemplateMapper.updateById(baiseeMessageTemplate);
	}

	@Override
	public BaiseeMessageTemplate findById(Long id) {
		return baiseeMessageTemplateMapper.selectById(id);
	}
	
	@Override
	public PageInfo<BaiseeMessageTemplate> findPage(int pageNum, int pageSize, BaiseeMessageTemplate baiseeMessageTemplate)
			throws OAServiceException {
		PageHelper.startPage(pageNum, pageSize);// 开始分页
		List<BaiseeMessageTemplate> list = baiseeMessageTemplateMapper.selectlist(baiseeMessageTemplate);

        return new PageInfo<BaiseeMessageTemplate>(list);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		baiseeMessageTemplateMapper.deleteById(id);
	}

	@Override
	public void send(BaiseeMessageTemplate baiseeMessageTemplate) {
		// TODO Auto-generated method stub
		String title = baiseeMessageTemplate.getTitle();
		String content = baiseeMessageTemplate.getContent();
		String type = baiseeMessageTemplate.getType();
		String fromUserId = baiseeMessageTemplate.getFromUserId();
		String fromUserName = baiseeMessageTemplate.getFromUserName();
		String isSendEmail = baiseeMessageTemplate.getIsSendEmail();
		String isSendPc = baiseeMessageTemplate.getIsSendPc();
		String isSendWechat = baiseeMessageTemplate.getIsSendWechat();
		
		String sendDeptList = baiseeMessageTemplate.getSendDeptList();
		String sendRoleList = baiseeMessageTemplate.getSendRoleList();
		String sendUserList = baiseeMessageTemplate.getSendUserList();
		
		List<String> toUserList = new ArrayList<String>();
		
		if(StringUtils.isNotBlank(sendDeptList)) {
			//加载部门用户
			String [] sendDeptIds = sendDeptList.split(",");
			for (String deptId : sendDeptIds) {
				
			}
		}
		
		if(StringUtils.isNotBlank(sendRoleList)){
			//加载角色用户
			String [] sendRoleIds = sendRoleList.split(",");
			for (String roleId : sendRoleIds) {
				
			}
		}
		if(StringUtils.isNotBlank(sendUserList)){
			//加载用户
			String [] sendUserIds = sendUserList.split(",");
			
			for (String userId : sendUserIds) {
				
				if(!toUserList.contains(userId)) {//过滤  重复用户
					toUserList.add(userId);
				}
				
			}
		}
		
		
		if("1".equals(isSendEmail)) {
			//发送到邮件
		}
		if("1".equals(isSendPc)) {
			BaiseeMessage baiseeMessage = new BaiseeMessage();
			baiseeMessage.setFromUserId(fromUserId);
			baiseeMessage.setFromUserName(fromUserName);
			baiseeMessage.setTitle(title);
			baiseeMessage.setContent(content);
			baiseeMessage.setType(type);
			baiseeMessage.setIsReader("0");
			baiseeMessage.setCreateTime(new Date());
			baiseeMessage.setTemplateId(baiseeMessageTemplate.getId());
			//发送到PC
			for (String userId : toUserList) {
				baiseeMessage.setReceiveUserId(userId);
				baiseeMessageService.insert(baiseeMessage);
			}
		}
		if("1".equals(isSendWechat)) {
			//发送到微信模板消息
		}
	}
}

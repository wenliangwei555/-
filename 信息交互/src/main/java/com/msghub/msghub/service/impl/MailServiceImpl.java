package com.msghub.msghub.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msghub.msghub.mapper.MailMapper;
import com.msghub.msghub.service.MailService;
@Service
public class MailServiceImpl implements MailService {

	private static MailMapper mapper = null;
	public MailServiceImpl(){}
	@Autowired
	public MailServiceImpl(MailMapper mapper) {
		MailServiceImpl.mapper = mapper;
	}

	@Override
	public String getText(String template_id) {
		return mapper.getText(template_id);
	}

	@Override
	public String[] getcompany(String client_id) {
		String fromEmail = "";   	// 发件人
        String authEmail = "";   	// 授权邮箱
        String authPaw = "";     	// 授权码
		fromEmail = mapper.getFromEmail(client_id);
		authPaw = mapper.geTauthPaw(client_id);
		authEmail = fromEmail;
		String [] company = {fromEmail,authEmail,authPaw};
		return company;
	}
	
	
	
}

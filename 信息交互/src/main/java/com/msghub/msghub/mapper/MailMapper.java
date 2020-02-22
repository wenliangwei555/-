package com.msghub.msghub.mapper;

import org.apache.ibatis.annotations.Mapper;

/**
 *  邮箱 mapper 接口
 */
@Mapper
public interface MailMapper {

	String getFromEmail(String client_id);

	String geTauthPaw(String client_id);
	
	String getText(String template_id);
	
}

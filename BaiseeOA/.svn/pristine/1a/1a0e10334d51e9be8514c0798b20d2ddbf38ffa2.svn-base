package cn.baisee.oa.utils;

import javax.servlet.http.HttpServletRequest;

import cn.baisee.oa.dao.baisee.BaiseeOALogMapper;
import cn.baisee.oa.model.BaiseeOALog;

public class LogUtil {
	
	public static void insetLog(HttpServletRequest request,String logResource,String logOperate,String logParameter){
		BaiseeOALog log=new BaiseeOALog();
		String userName=SessionUtil.getUserInfo(request).getUserName();
		log.setLogOperator(userName);
		log.setLogResource(logResource);
		log.setLogOperate(logOperate);
		log.setLogParameter(logParameter);
		BaiseeOALogMapper logMapper = (BaiseeOALogMapper)ApplicationContextUtil.getBean(BaiseeOALogMapper.class);
		logMapper.insertLog(log);
	}
}
  
package cn.baisee.oa.dao.baisee;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.BaiseeParent;

@Datasource(DatasourceTypes.OA)
public interface BaiseeParentMapper {
	
	/**
	 * 添加，如果该学生家长在这张表中没有信息就注册有就一存在
	 */
	public int addParent(BaiseeParent baiseeParent);
	
	
	//校验学生加长的手机号是否已经注册过
	public BaiseeParent getByPhone(String f_phone);
	
	//校验数据库中是否又该名称
	public BaiseeParent getByParent(String f_parent);
	
	public BaiseeParent getByOpenId(String f_openId);
	/**
	 * 根据sid查询出绑定过该学生的家长
	 * @param sid
	 * @return
	 */
	public List<BaiseeParent> getParent(String sid);
	

}

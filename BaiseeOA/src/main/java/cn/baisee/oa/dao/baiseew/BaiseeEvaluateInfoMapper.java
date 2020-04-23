package cn.baisee.oa.dao.baiseew;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.model.BaiseeEvaluateInfo;

@Datasource(DatasourceTypes.BAISEEW)
public interface BaiseeEvaluateInfoMapper {
	/**
	 * 列表查询
	 * @param sid
	 * @return
	 */
	List<BaiseeEvaluateInfo> selectAll(String sid);	
	
	public void insertDate(BaiseeEvaluateInfo insert);	//添加数据
	
	public BaiseeEvaluateInfo selectById(int evid);//根据id查询一条数据
	
	public void updateEVinfo(BaiseeEvaluateInfo info);//修改评论内容
	
	public void deleteEva(int[] evid);	//批量删除数据
	
	
	List<BaiseeEvaluateInfo> selectAllId(List<String> lit);
}

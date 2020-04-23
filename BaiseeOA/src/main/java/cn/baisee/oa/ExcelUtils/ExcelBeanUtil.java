package cn.baisee.oa.ExcelUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.baisee.oa.model.Insurance.BaiseeInsurance;
import cn.baisee.oa.model.repairReceive.BaiseeReceive;
import cn.baisee.oa.utils.DateUtil;

/**
 * 导入excel bean数据工具类
 * @author zhonglinsen
 *
 */
public class ExcelBeanUtil {

	/**
	 * 处理产品列表 塞入list-map 等待塞入excel的workbook进行处理
	 * @param products
	 * @return
	 */
	public static List<Map<Integer, Object>> manageProductList(final List<BaiseeInsurance> products){
		List<Map<Integer, Object>> dataList=new ArrayList<>();
		if (products!=null && products.size()>0) {
			int length=products.size();
			Map<Integer, Object> dataMap;
			BaiseeInsurance bean;
			for (int i = 0; i < length; i++) {
				bean=products.get(i);
				
				dataMap=new HashMap<>();
				dataMap.put(0, bean.getStuName()); /* 学生姓名*/
				if("1".equals(bean.getStuSex())) {
					dataMap.put(1, "女");  /*学生性别*/
				}else if ("0".equals(bean.getStuSex())) {
					dataMap.put(1, "男");  /*学生性别*/
				}
				dataMap.put(2, bean.getStuFamName());  /*学生家长姓名*/
				dataMap.put(3, bean.getClassName());  /*学生班级*/
				dataMap.put(4, bean.getStuIdNumber());/*学生身份证号*/
				dataMap.put(5, bean.getiMoney());  /* 保费*/
				if("0".equals(bean.getIsConTuition())) {
					dataMap.put(6, "未包含"); /*是否包含学费*/
				}else if ("1".equals(bean.getIsConTuition())) {
					dataMap.put(6, "包含"); /*是否包含学费*/
				}
				if("0".equals(bean.getStatus())) {
					dataMap.put(7, "未投保");  /* 状态*/
				}else if ("1".equals(bean.getStatus())) {
					dataMap.put(7, "投保中");  /* 状态*/
				}else if ("2".equals(bean.getStatus())) {
					dataMap.put(7, "已投保");  /* 状态*/
				}
				else if ("3".equals(bean.getStatus())) {
					dataMap.put(7, "已到期");  /* 状态*/
				}
				dataMap.put(8, bean.getiComName());  /*保险公司*/
				dataMap.put(9, bean.getiNo()); 		/*保单号*/
				dataMap.put(10, bean.getiInTime());	/*入保时间*/
				dataMap.put(11, bean.getiOutTime());	/*到期时间*/
				dataMap.put(12, bean.getCreateTime());/*创建时间*/
				
				dataList.add(dataMap);
			}
		}
		return dataList;
	}
	
	
	/**
	 *  按时间导出申领任务
	 * @param products
	 * @return
	 */
	public static List<Map<Integer, Object>> exportReceiveTaskList(final List<BaiseeReceive> receives){
		List<Map<Integer, Object>> dataList=new ArrayList<>();
		if (receives!=null && receives.size()>0) {
			int length=receives.size();
			Map<Integer, Object> dataMap;
			BaiseeReceive receive;
			for (int i = 0; i < length; i++) {
				receive=receives.get(i);
				dataMap=new HashMap<>();
				dataMap.put(0, receive.getcName()); //申领人名称
				dataMap.put(1, receive.getGoodsName());  //申领物品名称
				dataMap.put(2, receive.getGoodsNum());  //领取数量
				//领取状态
				if("0".equals(receive.getState())) {
					dataMap.put(3, "申报中");
				}else if ("1".equals(receive.getState())) {
					dataMap.put(3, "已领取"); 
				}else if ("2".equals(receive.getState())) {
					dataMap.put(3, "已拒绝");
				}
				dataMap.put(4, receive.getUpdatePerson());//处理人名称
				dataMap.put(5, receive.getRefuseReason()); //拒绝原因
				dataMap.put(6, DateUtil.formatDate("yyyy-MM-dd HH:mm:ss", receive.getCreateTime()));  //申领日期
				dataMap.put(7, DateUtil.formatDate("yyyy-MM-dd HH:mm:ss", receive.getUpdateTime()));  //申领日期
				dataList.add(dataMap);
			}
		}
		return dataList;
	}
	
}













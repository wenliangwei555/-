package cn.baisee.oa.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.baisee.oa.model.DetailAccount;
import cn.baisee.oa.page.pagehelper.PageInfo;
public interface BaiseeTransactionService {
	/**
	 * 正常全款缴费
	 * @param map
	 */
	void addFullPayment(Map<String, Object> map);
	/**
	 * 添加到预交费表
	 * 如果用户存在则修改预交的金额
	 * @param map
	 */
	void addPrepay(Map<String, Object> map);
	/**
	 * 退预交费
	 */
	void backPrepay(Map<String, Object> map);
	/**
	 * 抵充完预交费用删除预交用户信息
	 * @param userID
	 */
	void removePrepay(String userID,Integer balance);
	/**
	 * 根据用户编号查看预交的费用
	 * @param userID
	 * @return
	 */
	String readPrepay(String userID);
	/**
	 * 分期缴费
	 * @param map
	 */
	void installment(Map<String, Object> map);
	/**
	 * 记录返费流水
	 */
	void addReturnFee(Map<String, Object> map);
	/**
	 * 根据用户ID和交易类型获取详细账单
	 * @param userID 用户编号
	 * @param type 交易类型
	 * @return
	 */
	List<DetailAccount> getDetailAccount(String userID,String type);
	/**
	 * 根据用户编号和类型查询子表总金额
	 * @param userID
	 * @return
	 */
	String getPaymentAmount(String userID,String type);
	/**
	 * 根据用户编号和交易类型查询总金额
	 * @param userID
	 * @return
	 */
	String getTotalMoney(String userID,String type);
	/**
	 * 查看总流水详情
	 * @param userID
	 * @param type
	 * @return
	 */
	List<DetailAccount> selectTotalMoney(String userID,String type);
	/**
	 * 根据用户编号和缴费类型查看主表操作人以及子表的金额 备注  交易时间 （注：返费需要）
	 * @param userID
	 * @param type
	 * @return
	 */
	List<DetailAccount> readReturnFee(@Param("userID")String userID,@Param("type")String type);
	/**
	 * 根据交易流水号查看优惠表
	 * @param userID
	 * @param type
	 * @return
	 */
	List<DetailAccount> readPaymentDiscounts(String transactionID);
	/**
	 * 根据交易流水号查看抵充表
	 * @param userID
	 * @param type
	 * @return
	 */
	List<DetailAccount> readFillFlowMeter(String transactionID);
	/**
	 * 	根据日期、学生id查询总账单
	 * @param pageNum
	 * @param pageSize
	 * @param startTime
	 * @param endTime
	 * @param userid 学生id
	 * @return
	 */
	PageInfo<DetailAccount> getDateQueryTotalBill(int pageNum,int pageSize,String startTime,String endTime,String userid,String type);
	/**
	 * 根据交易流水号查看子流水
	 * @param transactionID
	 * @return
	 */
	List<DetailAccount> getDetail(String transactionID);
	/**
	 * 显示总收入和总支出
	 * @param startTime
	 * @param endTime
	 * @param userid
	 * @param type
	 * @return
	 */
	Map<String, Object> sumTotalBill(String startTime,String endTime,String userid,String type);
    /**
     * 查询总交费用+优惠费用
     * @param userID
     * @param tuition
     * @param audition
     * @return
     */
	String getTotalMoneyAndDiscounts(String userID,String tuition,String audition);

}

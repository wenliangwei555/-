package cn.baisee.oa.dao.baisee;



import java.util.List;
import java.util.Map;






import org.apache.ibatis.annotations.Param;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.DetailAccount;

/**
 * 交易流水接口
 * @author chenxuegang
 *
 */
@Datasource(DatasourceTypes.OA)
public interface BaiseeTransactionMapper {
	/**
	 * 一次性缴费添加信息到缴费流水表
	 */
	void insertPayment( Map<String, Object> map);
	/**
	 * 流水金额与业务表相同
	 * @param map
	 */
	void insePayment( Map<String, Object> map);
	/**.
	 * 添加信息到缴费业务表
	 * @param map
	 */
	void insertPaymentService( Map<String, Object> map);
	/**
	 *添加信息到缴费优惠表
	 */
	void insertPaymentDiscounts(Map<String, Object> map);
	/**
	 * 添加信息到抵充流水表
	 * @param map
	 */

	void insertFillFlowMeter(Map<String, Object> map);
	/**
	 * 添加信息到预缴费表
	 * @param map
	 */
	void insertAdvancePaymentTable(Map<String, Object> map);
	/**
	 * 根据用户编号查看预交的费用
	 * @return
	 */
	String selectPrepay(String userID);
	/**
	 * 查看用户是否存在
	 * @param userID
	 * @return
	 */
	String selectPrepayByID(String userID);
	/**
	 * 删除预交表用户信息
	 * @param userID
	 */
	void deletePrepayByID(String userID);
	/**
	 * 修改预交费用
	 * @param userID
	 */
	void updatePrepay(@Param("userID")String userID,@Param("amount")String amount);
	/**
	 * 根据用户ID和交易类型查询详细账单
	 * @param userID
	 * 
	 * @return
	 */
	List<DetailAccount> selectDetailAccount(@Param("userID")String userID,@Param("type")String type);
	/**
	 * 根据用户编号和类型查询子表总金额
	 * @param userID
	 * @return
	 */
    String selectPaymentAmount(@Param("userID")String userID,@Param("type")String type); 
    /**
     * 根据用户编号和交易类型查询总金额
     * @param userID
     * @param type
     * @return
     */
    String selectTotalMoney(@Param("userID")String userID,@Param("type")String type);
    /**
     * 查询总交费用+优惠费用
     * @param userID 用户名
     * @param tuition 学费  类型
     * @param audition 试听费 类型
     * @return
     */
    String selectTotalMoneyAndDiscounts(@Param("userID")String userID,@Param("tuition")String tuition,@Param("audition")String audition);
    /**
     * 根据用户编号和交易类型查询总金额用于前台展示
     * @param userID
     * @param type
     * @return
     */
    List<DetailAccount> readTotalMoney(@Param("userID")String userID,@Param("type")String type);
	/**
	 * 生成交易流水号
	 * @return
	 */
	String selectSN();
	/**
	 * 生成业务流水号
	 * @return
	 */
	String selectNO();

	/**
	 * 根据用户编号和缴费类型查看主表操作人以及子表的金额 备注  交易时间 （注：返费需要）
	 * @return
	 */
	List<DetailAccount> selectReturnFee(@Param("userID")String userID,@Param("type")String type);
	/**
	 * 根据交易流水号查看优惠表
	 * @param userID
	 * @param type
	 * @return
	 */
	 List<DetailAccount> selectPaymentDiscounts(String transactionID);
	 /**
	  * 根据交易流水号查看抵充表
	  * @param userID
	  * @param type
	  * @return
	  */
	 List<DetailAccount> selectFillFlowMeter(String transactionID);
	 /**
	  * 根据日期、学生id查询总账单
	  * @return
	  */
	 List<DetailAccount> selectDateQueryTotalBill( @Param("type")String type,@Param("startTime")String startTime, @Param("endTime")String endTime,@Param("userid")String userid);
	 /**
	  * 根据交易流水号查看子流水
	  * @param transactionID
	  * @return
	  */
	 List<DetailAccount> selectDetail(String transactionID);
	 
}

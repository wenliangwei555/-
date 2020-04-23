package cn.baisee.oa.dao.baisee;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.BaiseeNotice;
import cn.baisee.oa.model.BaiseeNoticeStaff;
import cn.baisee.oa.service.BaiseeNoticeService;

/**
 * 系统通知信息mapper接口类
 *
 * @author dongchao
 */
@Datasource(DatasourceTypes.OA)
public interface BaiseeNoticeMapper {
    /**
     * 根据id 查询
     *
     * @param ID
     * @return 通知信息表基本信息
     */
    public List<BaiseeNotice> selectById(Map<String, Object> map);

    /**
     * 获取某个用户的通知信息
     *
     * @param 用户ID
     * @return 用户通知信息表基本信息
     */
    public List<BaiseeNotice> selectByUserId(Map<String, Object> map);

    /**
     * 获取展示中的 系统通知列表
     *
     * @return
     */
    public List<BaiseeNotice> selectSystemNotice();

    /**
     * 获取集合数据
     *
     * @return 获取通知集合数据
     */
    public List<BaiseeNotice> selectBaiseeNoticePage(BaiseeNotice notice);

    /**
     * 状态更新
     *
     * @param ID
     * @param STATUS
     * @return
     */
    public int updateStatus(Map<String, Object> map);

    /**
     * 插入
     *
     * @param notice
     * @return
     */
    public int insert(BaiseeNotice notice);

    /**
     * 状态更新
     * 修改状态为 已读
     *
     * @param ID
     * @return
     */
    public int readNotice(Map<String, Object> map);

    /**
     * 状态更新
     * 修改状态为 已处理
     *
     * @param ID
     * @return
     */
    public int handleNotice(Map<String, Object> map);

    /**
     * 状态更新
     * 修改状态为 已关闭
     *
     * @param ID
     * @return
     */
    public int closeNotice(Map<String, Object> map);

    /**
     * 根据用户主键和信件状态查看
     *
     * @param map
     * @return
     */
    public List<BaiseeNotice> selectByUserIdAndType(String userId);

    public List<BaiseeNoticeStaff> selectType(String userId);
}

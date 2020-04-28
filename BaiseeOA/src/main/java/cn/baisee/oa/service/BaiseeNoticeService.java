package cn.baisee.oa.service;

import java.util.List;

import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.model.BaiseeNotice;
import cn.baisee.oa.model.BaiseeNoticeStaff;
import cn.baisee.oa.page.pagehelper.PageInfo;

public interface BaiseeNoticeService {

    public void insertNotice(BaiseeNotice notice) throws OAServiceException;

    /**
     * 分页显示通知里面的信息
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageInfo<BaiseeNotice> selectNoticeList(int pageNum, int pageSize,
                                                   BaiseeNotice notice) throws OAServiceException;

    public boolean delNotice(String noticeId) throws OAServiceException;

    public void delAllNotice(String[] ids) throws OAServiceException;

    /**
     * 根据id 获取通知
     *
     * @param noticeId
     * @return
     * @throws OAServiceException
     */
    public BaiseeNotice getNoticeById(String noticeId) throws OAServiceException;

    /**
     * 根据用户id 获取通知列表
     *
     * @param userId
     * @return
     * @throws OAServiceException
     */
    public List<BaiseeNotice> selectNoticeByUserId(String userId) throws OAServiceException;

    /**
     * 获取展示当中的系统通知列表
     *
     * @param userId
     * @return
     * @throws OAServiceException
     */
    public List<BaiseeNotice> selectSystemNotice() throws OAServiceException;

    /**
     * 标识 通知为已读
     *
     * @param noticeId
     * @return
     */
    public int readNotice(String noticeId);

    /**
     * 标识 通知为已处理
     *
     * @param noticeId
     * @return
     */
    public int handleNotice(String noticeId);

    /**
     * 发送通知
     *
     * @param notice
     * @return
     */
    public int send(BaiseeNotice notice);

    /**
     * 发送批量通知（发送给所有用户）
     *
     * @param notice
     * @return
     */
    public int sendAll(BaiseeNotice notice);

    /**
     * 关闭系统通知
     *
     * @param noticeId
     * @return
     */
    public int closeNotice(String noticeId);

    /**
     * 查看该用户的信息
     *
     * @param userId
     * @return
     */
    public List<BaiseeNotice> selectByUserIdAndType(String userId);


    public List<BaiseeNoticeStaff> selectType(String userId);
}

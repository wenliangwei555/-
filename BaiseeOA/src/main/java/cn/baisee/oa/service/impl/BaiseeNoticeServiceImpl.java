package cn.baisee.oa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.baisee.oa.model.BaiseeNoticeStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.baisee.BaiseeNoticeMapper;
import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.model.BaiseeNotice;
import cn.baisee.oa.page.pagehelper.PageHelper;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeNoticeService;
import cn.baisee.oa.utils.StringUtil;

/**
 * @author dongchao
 */
@Service
public class BaiseeNoticeServiceImpl implements BaiseeNoticeService {

    @Autowired
    private BaiseeNoticeMapper baiseeNoticeMapper;

    @Override
    public void insertNotice(BaiseeNotice notice) throws OAServiceException {
        // TODO Auto-generated method stub

    }

    @Override
    public PageInfo<BaiseeNotice> selectNoticeList(int pageNum, int pageSize, BaiseeNotice notice) throws OAServiceException {
        PageHelper.startPage(pageNum, pageSize);// 开始分页

        BaiseeNotice condtion = new BaiseeNotice();
        if (StringUtil.isNotEmpty(notice.getStatus())) {
            condtion.setStatus(notice.getStatus());
            if ("*".equals(notice.getStatus())) {
                //如果是查询所有
                condtion.setStatus(null);
            }
        }
        if (StringUtil.isNotEmpty(notice.getCreateStartTime())) {
            condtion.setCreateStartTime(notice.getCreateStartTime().replace("-", ""));
        }
        if (StringUtil.isNotEmpty(notice.getCreateEndTime())) {
            condtion.setCreateEndTime(notice.getCreateEndTime().replace("-", ""));
        }

        if (StringUtil.isNotEmpty(notice.getUpdateStartTime())) {
            condtion.setCreateStartTime(notice.getCreateStartTime().replace("-", ""));
        }
        if (StringUtil.isNotEmpty(notice.getUpdateEndTime())) {
            condtion.setUpdateEndTime(notice.getUpdateEndTime().replace("-", ""));
        }
        if (StringUtil.isNotEmpty(notice.getToUserId())) {
            condtion.setToUserId(notice.getToUserId());
        }
        if (StringUtil.isNotEmpty(notice.getToUserName())) {
            condtion.setToUserName(notice.getToUserName());
        }
        if (StringUtil.isNotEmpty(notice.getFromUserId())) {
            condtion.setFromUserId(notice.getFromUserId());
        }
        if (StringUtil.isNotEmpty(notice.getToUserName())) {
            condtion.setFromUserName(notice.getToUserName());
        }
        if (null != notice.getId()) {

            condtion.setId(notice.getId());
        }

        List<BaiseeNotice> list = baiseeNoticeMapper.selectBaiseeNoticePage(condtion);

        return new PageInfo<BaiseeNotice>(list);
    }

    @Override
    public boolean delNotice(String noticeId) throws OAServiceException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void delAllNotice(String[] ids) throws OAServiceException {
        // TODO Auto-generated method stub

    }

    @Override
    public BaiseeNotice getNoticeById(String noticeId) throws OAServiceException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ID", noticeId);
        List<BaiseeNotice> list = baiseeNoticeMapper.selectById(map);
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<BaiseeNotice> selectNoticeByUserId(String userId) throws OAServiceException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("TO_USER_ID", userId);
        return baiseeNoticeMapper.selectByUserId(map);
    }

    @Override
    public int readNotice(String noticeId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ID", noticeId);
        return baiseeNoticeMapper.readNotice(map);
    }

    @Override
    public int handleNotice(String noticeId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ID", noticeId);
        return baiseeNoticeMapper.handleNotice(map);
    }

    @Override
    public int send(BaiseeNotice notice) {
        return baiseeNoticeMapper.insert(notice);
    }

    @Override
    public int sendAll(BaiseeNotice notice) {
        return baiseeNoticeMapper.insert(notice);
    }

    @Override
    public int closeNotice(String noticeId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ID", noticeId);
        return baiseeNoticeMapper.closeNotice(map);
    }

    @Override
    public List<BaiseeNotice> selectSystemNotice() throws OAServiceException {
        return baiseeNoticeMapper.selectSystemNotice();
    }

    @Override
    public List<BaiseeNotice> selectByUserIdAndType(String userId) {
        // TODO Auto-generated method stub
        return baiseeNoticeMapper.selectByUserIdAndType(userId);
    }

    @Override
    public List<BaiseeNoticeStaff> selectType(String userId) {
        List<BaiseeNoticeStaff> list = baiseeNoticeMapper.selectType(userId);
        return list;
    }

}

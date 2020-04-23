package cn.baisee.oa.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.baisee.oa.model.BaiseeNoticeStaff;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.exception.OAServiceException;
import cn.baisee.oa.model.BaiseeUser;
import cn.baisee.oa.model.BaiseeNotice;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeNoticeService;
import cn.baisee.oa.service.UserService;
import cn.baisee.oa.utils.ParamUtils;
import cn.baisee.oa.utils.RedisUtils;
import cn.baisee.oa.utils.SessionUtil;
import cn.baisee.oa.utils.StringUtil;

@Controller
@RequestMapping("/notice")
@SuppressWarnings({"rawtypes", "unchecked"})
public class BaiseeNoticeController {
    /**
     * 用户通知 缓存key前缀
     */
    private static final String USER_NOTICE_CACHE_PREFIX = "USER_NOTICE_CACHE_PREFIX";
    /**
     * 系统通知 缓存key
     */
    private static final String SYSTEM_NOTICE_CACHE_KEY = "SYSTEM_NOTICE_CACHE_KEY";
    // 缓存的工具类
    @Resource(name = "redisUtils")
    private RedisUtils redisUtils;
    @Autowired
    private BaiseeNoticeService baiseeUserNoticeService;

    @Autowired
    private UserService userService;

    /**
     * 获取当前登录用户的可查看通知列表
     *
     * @param request
     * @return
     */

    @ResponseBody
    @RequestMapping("/getCurrUserNoticeList")
    @Role(ignore = true)
    public List<BaiseeNotice> getCurrUserNoticeList(HttpServletRequest request) throws OAServiceException {

        BaiseeUser currUser = (BaiseeUser) SessionUtil.getUserInfo(request);
        String key = USER_NOTICE_CACHE_PREFIX + currUser.getUserId();

        List<BaiseeNotice> userNoticeList = null;
        if (redisUtils.exists(key)) {
            userNoticeList = (List) redisUtils.get(key);
            System.out.println(String.format("加载用户【%s】通知，取缓存key=【%s】,通知数量=【%s】", currUser.getUserName(), key, userNoticeList.size()));
        } else {
            userNoticeList = baiseeUserNoticeService.selectNoticeByUserId(currUser.getUserId());
            redisUtils.set(key, userNoticeList, 60 * 30L);//10分钟更新一次缓存
            System.out.println(String.format("加载用户【%s】通知，取数据库key=【%s】,通知数量=【%s】", currUser.getUserName(), key, userNoticeList.size()));
        }

        List<BaiseeNotice> systemNoticeList = null;
        //加载系统通知
        if (redisUtils.exists(SYSTEM_NOTICE_CACHE_KEY)) {
            systemNoticeList = (List<BaiseeNotice>) redisUtils.get(SYSTEM_NOTICE_CACHE_KEY);
            System.out.println(String.format("加载系统通知，取缓存key=【%s】,通知数量=【%s】", SYSTEM_NOTICE_CACHE_KEY, systemNoticeList.size()));
        } else {
            systemNoticeList = baiseeUserNoticeService.selectSystemNotice();
            redisUtils.set(SYSTEM_NOTICE_CACHE_KEY, systemNoticeList, 60 * 30L);//10分钟更新一次缓存
            System.out.println(String.format("加载系统通知，取缓存key=【%s】,通知数量=【%s】", SYSTEM_NOTICE_CACHE_KEY, systemNoticeList.size()));
        }

        systemNoticeList.addAll(userNoticeList);

        return systemNoticeList;
    }

    /**
     * 系统通知_通知列表
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws OAServiceException
     */
    @RequestMapping("/list")
    @Role(value = "XTTZ_TZLB")
    public String list(BaiseeNotice notice, HttpServletRequest request, HttpServletResponse response, Model model) throws OAServiceException {

        Integer pageNum = ParamUtils.getPageNumParameters(request);
        Integer pageSize = ParamUtils.getPageSizeParameters(request);

        //只查询当前用户可以查看的
        BaiseeUser currUser = (BaiseeUser) SessionUtil.getUserInfo(request);
        notice.setToUserId(currUser.getUserId());
        System.out.println(String.format("startTime=[%s]", notice.getCreateStartTime()));
        System.out.println(String.format("endTime=[%s]", notice.getCreateEndTime()));
        System.out.println(String.format("currUserId =-=== %s", currUser.getUserId()));
        //默认查询 未读的通知
        if (StringUtils.isBlank(notice.getStatus())) {
            notice.setStatus(BaiseeNotice.NOTICE_STATUS_0);
        }

        PageInfo<BaiseeNotice> pageInfo = baiseeUserNoticeService.selectNoticeList(pageNum, pageSize, notice);
        model.addAttribute("notice", notice);
        model.addAttribute("pageResult", pageInfo);//设置参数pageInfo页面显示数据
        // 提示信息
        List<BaiseeNotice> list = new ArrayList<>();
        List<BaiseeNoticeStaff> baiseeNoticeStaff = baiseeUserNoticeService.selectType(currUser.getUserId());
        if (baiseeNoticeStaff.size() > 0) {
            for (BaiseeNoticeStaff b : baiseeNoticeStaff) {
                BaiseeNotice bn = new BaiseeNotice();
                bn.setFromUserId(b.getCreate_id());
                bn.setFromUserName(b.getName());
                bn.setTitle(b.getT_title());
                bn.setContent(b.getT_content());
                list.add(bn);
            }
        }
        model.addAttribute("msgSize", list.size());// 提示信息的长度
        return "notice/notice_list";
    }

    /**
     * 系统通知_通知列表_弹框显示
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws OAServiceException
     */
    @RequestMapping("/list2")
    @Role(value = "XTTZ_TZLB")
    public String list2(HttpServletRequest request, HttpServletResponse response, Model model) throws OAServiceException {
        //只查询当前用户可以查看的
        BaiseeUser currUser = (BaiseeUser) SessionUtil.getUserInfo(request);
        List<BaiseeNotice> list = new ArrayList<>();
        List<BaiseeNoticeStaff> baiseeNoticeStaff = baiseeUserNoticeService.selectType(currUser.getUserId());
        if (baiseeNoticeStaff.size() > 0) {
            for (BaiseeNoticeStaff b : baiseeNoticeStaff) {
                BaiseeNotice bn = new BaiseeNotice();
                bn.setFromUserId(b.getCreate_id());
                bn.setFromUserName(b.getName());
                bn.setTitle(b.getT_title());
                bn.setContent(b.getT_content());
                list.add(bn);
            }
        }
        model.addAttribute("list", list);//设置参数pageInfo页面显示数据
        return "notice/notice_list2";
    }


    /**
     * 系统通知_我的发送_通知列表
     *
     * @param request
     * @param response
     * @param model
     * @return
     * @throws OAServiceException
     */
    @RequestMapping("/sendlist")
    @Role(value = "XTTZ_TZLB_FS")
    public String sendlist(BaiseeNotice notice, HttpServletRequest request, HttpServletResponse response, Model model) throws OAServiceException {

        Integer pageNum = ParamUtils.getPageNumParameters(request);
        Integer pageSize = ParamUtils.getPageSizeParameters(request);

        //只查询当前用户可以查看的
        BaiseeUser currUser = (BaiseeUser) SessionUtil.getUserInfo(request);
        notice.setFromUserId(currUser.getUserId());

        PageInfo<BaiseeNotice> pageInfo = baiseeUserNoticeService.selectNoticeList(pageNum, pageSize, notice);
        model.addAttribute("notice", notice);
        model.addAttribute("pageResult", pageInfo);//设置参数pageInfo页面显示数据

        return "notice/notice_send_list";
    }

    /**
     * 系统通知_通知查看
     *
     * @param request
     * @return
     * @throws OAServiceException
     */
    @RequestMapping("/view")
    @Role(value = "XTTZ_TZCK")
    public String view(HttpServletRequest request, Model model) throws OAServiceException {

        String noticeId = ParamUtils.getParameter(request, "noticeId");
        //获取通知信息并验证
        BaiseeNotice notice = baiseeUserNoticeService.getNoticeById(noticeId);
        if (null == notice) {
            throw new OAServiceException(String.format("通知【%s】不存在", noticeId));
        }
        BaiseeUser currUser = (BaiseeUser) SessionUtil.getUserInfo(request);
        model.addAttribute("currUser", currUser);

        if (currUser.getUserId().equals(notice.getToUserId())) {
            String key = USER_NOTICE_CACHE_PREFIX + currUser.getUserId();
            //标识通知状态为 已读
            baiseeUserNoticeService.readNotice(noticeId);
            redisUtils.del(key);//清除缓存
            //状态修改， 重新获取通知信息
            notice = baiseeUserNoticeService.getNoticeById(noticeId);
        }
        model.addAttribute("info", notice);
        return "notice/notice_view";
    }


    /**
     * 系统通知_通知处理
     *
     * @param request
     * @return
     * @throws OAServiceException
     */
    @RequestMapping(value = "/handle", method = RequestMethod.POST)
    @ResponseBody
    @Role(value = "XTTZ_TZCL")
    public Map<String, String> handle(HttpServletRequest request, Model model) throws OAServiceException {

        Map<String, String> resultMap = new HashMap<String, String>();

        String noticeId = ParamUtils.getParameter(request, "noticeId");

        if (StringUtil.isBlank(noticeId)) {
            resultMap.put("code", "FAIL");
            resultMap.put("msg", "操作失败，参数错误");
            return resultMap;
        }
        BaiseeNotice notice = baiseeUserNoticeService.getNoticeById(noticeId);
        if (null == notice) {
            resultMap.put("code", "FAIL");
            resultMap.put("msg", "操作失败，没找到这条通知！");
            return resultMap;
        }

        BaiseeUser currUser = (BaiseeUser) SessionUtil.getUserInfo(request);

        if (!notice.getToUserId().equals(currUser.getUserId())) {
            resultMap.put("code", "FAIL");
            resultMap.put("msg", "操作失败，这条通知不属于你！");
            return resultMap;
        }
        //标识通知状态为 已处理
        baiseeUserNoticeService.handleNotice(noticeId);

        redisUtils.del(USER_NOTICE_CACHE_PREFIX + currUser.getUserId());//清除缓存

        resultMap.put("code", "SUCCESS");
        resultMap.put("msg", "操作成功");
        return resultMap;
    }

    /**
     * 系统通知_关闭系统通知
     *
     * @param request
     * @return
     * @throws OAServiceException
     */
    @RequestMapping(value = "/closeNotice", method = RequestMethod.POST)
    @ResponseBody
    @Role(value = "XTTZ_FSTZ")
    public Map<String, String> closeNotice(HttpServletRequest request, Model model) throws OAServiceException {

        Map<String, String> resultMap = new HashMap<String, String>();

        String noticeId = ParamUtils.getParameter(request, "noticeId");

        if (StringUtil.isBlank(noticeId)) {
            resultMap.put("code", "FAIL");
            resultMap.put("msg", "操作失败，参数错误");
            return resultMap;
        }
        BaiseeNotice notice = baiseeUserNoticeService.getNoticeById(noticeId);
        if (null == notice) {
            resultMap.put("code", "FAIL");
            resultMap.put("msg", "操作失败，找不到该通知");
            return resultMap;
        }
        BaiseeUser currUser = (BaiseeUser) SessionUtil.getUserInfo(request);
        if (!notice.getFromUserId().equals(currUser.getUserId())) {
            resultMap.put("code", "FAIL");
            resultMap.put("msg", "操作失败，非法操作");
            return resultMap;
        }
        //标识通知状态为 已关闭
        baiseeUserNoticeService.closeNotice(noticeId);

        //刷新缓存
        if (StringUtil.isNotEmpty(notice.getToUserId())) {
            redisUtils.del(USER_NOTICE_CACHE_PREFIX + notice.getToUserId());//清除缓存
        } else {
            //没有 接收人则为 系统通知
            redisUtils.del(SYSTEM_NOTICE_CACHE_KEY);//清除缓存
        }
        resultMap.put("code", "SUCCESS");
        resultMap.put("msg", "操作成功,该操作会存在延时。十分钟左右。");
        return resultMap;
    }

    /**
     * 发送通知 页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/sendview")
    @Role(value = "XTTZ_FSTZ")
    public String sendview(HttpServletRequest request, Model model) throws OAServiceException {

        return "notice/notice_send_view";
    }

    /**
     * 发送通知
     *
     * @param title    标题
     * @param content  内容
     * @param toUserId 接收人
     * @return
     * @throws Exception
     */
    @RequestMapping("/send")
    @ResponseBody
    @Role(value = "XTTZ_FSTZ")
    public Map<String, String> send(HttpServletRequest request, Model model) throws Exception {
        Map<String, String> resultMap = new HashMap<String, String>();

        //通知标题
        final String title = ParamUtils.getParameter(request, "title");
        //通知内容
        final String content = ParamUtils.getParameter(request, "content");
        //接收人id
        final String[] toUsers = request.getParameterValues("toUserId");
        //String toUserId = ParamUtils.getParameter(request, "toUserId");
        final BaiseeUser currUser = (BaiseeUser) SessionUtil.getUserInfo(request);
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    BaiseeNotice notice = null;
                    BaiseeUser toUser = null;
                    for (String _u : toUsers) {
                        notice = new BaiseeNotice();
                        notice.setTitle(title);
                        notice.setStatus(BaiseeNotice.NOTICE_STATUS_0);
                        notice.setContent(content);
                        notice.setFromUserId(currUser.getUserId());
                        notice.setFromUserName(currUser.getUserName());
                        toUser = userService.getUserById(_u);
                        notice.setToUserId(toUser.getUserId());
                        notice.setToUserName(toUser.getUserName());

                        baiseeUserNoticeService.send(notice);
                        redisUtils.del(USER_NOTICE_CACHE_PREFIX + _u);//清除缓存
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


        resultMap.put("code", "SUCCESS");
        resultMap.put("msg", "发送成功");
        return resultMap;
    }

    /**
     * 给所有用户发送通知
     *
     * @param title   标题
     * @param content 内容
     * @return
     * @throws OAServiceException
     */
    @RequestMapping("/sendAll")
    @ResponseBody
    @Role(value = "XTTZ_FSTZALL")
    public Map<String, String> sendALL(HttpServletRequest request, Model model) throws OAServiceException {
        Map<String, String> resultMap = new HashMap<String, String>();

        BaiseeUser currUser = (BaiseeUser) SessionUtil.getUserInfo(request);
        String title = ParamUtils.getParameter(request, "title");
        String content = ParamUtils.getParameter(request, "content");
        BaiseeNotice notice = new BaiseeNotice();
        notice.setTitle(title);
        notice.setStatus(BaiseeNotice.NOTICE_STATUS_0);//状态为  未读
        notice.setContent(content);
        notice.setFromUserId(currUser.getUserId());
        notice.setFromUserName(currUser.getUserName());
        //发送通知
        baiseeUserNoticeService.sendAll(notice);

        redisUtils.del(SYSTEM_NOTICE_CACHE_KEY);//清除缓存
        resultMap.put("code", "SUCCESS");
        resultMap.put("msg", "发送成功");
        return resultMap;
    }
}

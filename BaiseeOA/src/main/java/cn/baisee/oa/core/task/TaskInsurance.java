package cn.baisee.oa.core.task;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.Session;

import cn.baisee.oa.constants.UploadFileVideo;
import cn.baisee.oa.core.context.FileConnParamLoadHelper;
import cn.baisee.oa.core.file.SSHClientUtil;
import cn.baisee.oa.dao.baisee.BaiseeClazzMapper;
import cn.baisee.oa.dao.baisee.BaiseeInsuranceMapper;
import cn.baisee.oa.dao.baisee.BaiseeNoticeMapper;
import cn.baisee.oa.dao.baisee.BaiseeUserMapper;
import cn.baisee.oa.model.BaiseeClazz;
import cn.baisee.oa.model.BaiseeNotice;
import cn.baisee.oa.model.Insurance.BaiseeInsurance;
import cn.baisee.oa.utils.DateUtil;
import cn.baisee.oa.utils.PropertiesUtil;

@Component("TaskInsurance")
public class TaskInsurance {
	@Autowired
	private BaiseeClazzMapper baiseeClazzMapper;

	@Autowired
	private BaiseeInsuranceMapper insuranceMapper;
	@Autowired
	private BaiseeNoticeMapper baiseeNoticeMapper;
	@Autowired
	private BaiseeUserMapper baiseeUserMapper;

	/**
	 * 每天凌晨执行一次
	 */
	@Scheduled(cron = "0 0 0 * * ?")
	public void queryAndUpdateInsurance() {
		String reqDate = DateUtil.getReqDate();
		List<BaiseeInsurance> list = insuranceMapper.queryAllInsurance(); /* 查询所有人的保险到期信息 */
		for (BaiseeInsurance baiseeInsurance : list) {
			/*
			 * if (baiseeInsurance.getiOutTime() != null &&
			 * !"".equals(baiseeInsurance.getiOutTime()) &&
			 * baiseeInsurance.getiOutTime().equals(reqDate) &&
			 * !"3".equals(baiseeInsurance.getStatus())) {
			 * insuranceMapper.updateExpireInsurance(baiseeInsurance.getId(),"3"
			 * ); 修改到期保险状态 }
			 */
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String ioutTime = baiseeInsurance.getiOutTime();
			try {
				// 用保险到期时间和当前时间作比较
				boolean isOverdue = false;
				if (ioutTime != "" && ioutTime != null) {
					isOverdue = sdf.parse(ioutTime).compareTo(sdf.parse(reqDate)) < 0;
				}
				if (baiseeInsurance.getiOutTime() != null && !"".equals(baiseeInsurance.getiOutTime()) && isOverdue
						&& !"3".equals(baiseeInsurance.getStatus())) {
					insuranceMapper.updateExpireInsurance(baiseeInsurance.getId(), "3"); /* 修改到期保险状态 */
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 每天凌晨执行一次保险到期学生名单
	 */
	@Scheduled(cron = "0 30 0 * * ?")
	public void expirationNotice() {
		BaiseeNotice notice = null;
		List<String> teacherList = new ArrayList<String>();
		byte g = 1;
		byte c = 1;
		byte i = 1;
		String week = DateUtil.getDateWeek();// 一周后时间
		List<BaiseeInsurance> list = insuranceMapper.queryAllInsurance(); /* 查询所有人的保险到期信息 */
		for (BaiseeInsurance baiseeInsurance : list) {
			if (baiseeInsurance.getiOutTime() != null && !"".equals(baiseeInsurance.getiOutTime())
					&& baiseeInsurance.getiOutTime().equals(week) && !"3".equals(baiseeInsurance.getStatus())
					&& !"4".equals(baiseeInsurance.getStatus())) {// 保险还有7天就到期的学生
				insuranceMapper.updateExpireInsurance(baiseeInsurance.getId(), "4"); // 修改保险状态成快到期
				i++;
				String classId = baiseeInsurance.getClassId();// 学生的班级
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("cId", classId);
				BaiseeClazz clazz = baiseeClazzMapper.getClaById(map);// 获取班级信息
				String status = clazz.getcStatus();// 高中或初中
				if ("0".equals(status)) {
					c++;
				} else {
					g++;
				}
				String teacherNumber = clazz.getcTeacherNumber();// 获取学生班主任
				teacherList.add(teacherNumber);// 把学生班主任放入集合
			}
		}
		if (g > 1) {// 给高中年纪主任发送通知
			String[] high = baiseeUserMapper.selectUserByRoleId("OA20181212142400359");
			for (String str : high) {
				notice = new BaiseeNotice();
				notice.setTitle("保险到期通知");
				notice.setStatus(BaiseeNotice.NOTICE_STATUS_0);
				notice.setContent("请注意,有学生保险即将到期,请前往保险管理筛选条件查看具体学生！");
				notice.setFromUserId("EMID20180919114009809");
				notice.setFromUserName("百思");
				notice.setToUserId(str);
				baiseeNoticeMapper.insert(notice);

			}
		}
		if (c > 1) {// 给初中年级主任发送通知
			String[] junior = baiseeUserMapper.selectUserByRoleId("OA20181212142249789");

			for (String str : junior) {
				notice = new BaiseeNotice();
				notice.setTitle("保险到期通知");
				notice.setStatus(BaiseeNotice.NOTICE_STATUS_0);
				notice.setContent("请注意,有学生保险即将到期,请前往保险管理筛选条件查看具体学生！");
				notice.setFromUserId("EMID20180919114009809");
				notice.setFromUserName("百思");
				notice.setToUserId(str);
				baiseeNoticeMapper.insert(notice);

			}
		}
		if (!teacherList.isEmpty()) {// 给班主任发通知
			for (String str : teacherList) {
				notice = new BaiseeNotice();
				notice.setTitle("保险到期通知");
				notice.setStatus(BaiseeNotice.NOTICE_STATUS_0);
				notice.setContent("请注意,有学生保险即将到期,请前往保险管理筛选条件查看具体学生！");
				notice.setFromUserId("EMID20180919114009809");
				notice.setFromUserName("百思");
				notice.setToUserId(str);
				baiseeNoticeMapper.insert(notice);
			}

		}
		if (i > 1) {
			// 给财务发通知
			String[] financial = baiseeUserMapper.selectUserByRoleId("OA20181212151707187");// 获得财务人的ID
			for (String str : financial) {
				notice = new BaiseeNotice();
				notice.setTitle("保险到期通知");
				notice.setStatus(BaiseeNotice.NOTICE_STATUS_0);
				notice.setContent("请注意,有学生保险即将到期,请前往保险管理筛选条件查看具体学生！");
				notice.setFromUserId("EMID20180919114009809");
				notice.setFromUserName("百思");
				notice.setToUserId(str);
				baiseeNoticeMapper.insert(notice);

			}

		}
	}

	/**
	 * 每天凌晨执行一次,删除预览时生成的PDF
	 */
	@Scheduled(cron = "0 0 0 * * ?")
	public void deleteTmpPdf() {
		ChannelExec channelExec = null;
		// 获取档案上传档案根目录
		PropertiesUtil p = new PropertiesUtil("ftp.properties");
		String tagrtDir = p.get("dossier.out.path");// 转换后输出的路径
		try {
			Session session = FileConnParamLoadHelper.getSshSession("sftpConfig02");
			channelExec = SSHClientUtil.openChannelExec(session, UploadFileVideo.CHANNELTYPEEXEC);
			InputStream in = channelExec.getInputStream();
			channelExec.setCommand(" rm -rf " + tagrtDir + "*.pdf");
			Thread.sleep(100);
			channelExec.setErrStream(System.err);
			channelExec.connect();
			IOUtils.toString(in);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (channelExec != null) {
				channelExec.disconnect();
			}
		}
	}
}

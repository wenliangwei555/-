package cn.baisee.oa.utils;

import cn.baisee.oa.model.BaiseeLeaveRecord;
import cn.baisee.oa.model.BaiseeNoticeStaff;
import net.sf.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 模板工具类
 * liangfeng
 */
public class TemplateUtil {
	
	
	/**
	 * 发送学生成绩模板消息
	 */
	public static String postScore(String sname1,double scoer11,double scoer21,double scoer31,double scoer41,String openId,String Token,Date time) {
		PropertiesUtil p = new PropertiesUtil("WeChat.properties");
        JSONObject jsonObject = new JSONObject();
        String appId = p.get("appID");
        String url1 = p.get("url");
        String template_id = p.get("stu_score_temlate_id1");
		JSONObject  scoreJson = new JSONObject();
		scoreJson.put("touser",openId);
		scoreJson.put("template_id", template_id);
		scoreJson.put("url","https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appId+"&redirect_uri=http%3a%2f%2f"+url1+"%2ftoWeChatGetParent&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
		JSONObject data = new JSONObject();
		JSONObject sname = new JSONObject();
		sname.put("value",sname1);
		sname.put("color", "173177");
		data.put("sname", sname);
		
		//开头
		JSONObject first = new JSONObject();
		first.put("value", "您好，学生："+sname1+"的考试成绩！");
		first.put("color", "173177");
		data.put("first", first);
		
		//结尾
		JSONObject remark = new JSONObject();
		remark.put("value", "");
		remark.put("color", "173177");
		data.put("remark", remark);
		
		//考试时间
		JSONObject keyword1 = new JSONObject();
		keyword1.put("value", DateUtil.formatDate("yyyy-MM-dd",time));
		keyword1.put("color", "173177");
		data.put("keyword1", keyword1);
		
		//语文
		JSONObject keyword2 = new JSONObject();
		keyword2.put("value", scoer21==0?"未考":scoer21);
		keyword2.put("color", "173177");
		data.put("keyword2", keyword2);
		
		//数学
		JSONObject keyword3 = new JSONObject();
		keyword3.put("value",  scoer31==0?"未考":scoer31);
		keyword3.put("color", "173177");
		data.put("keyword3", keyword3);
		
		//英语
		JSONObject keyword4 = new JSONObject();
		keyword4.put("value",  scoer41==0?"未考":scoer41);
		keyword4.put("color", "173177");
		data.put("keyword4", keyword4);
		scoreJson.put("data",data);
		
		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+Token;
		String r = HttpUtil.doPostJson(url, scoreJson);
		return r;
	}
    /**
     * 发送请假模板消息
     */
    public static void sendLeaveTemplateInfo(String openId, BaiseeLeaveRecord baiseeLeaveRecord, String access_token) throws Exception {
        //access_token
    	String a = CommonUtil.getAccessToken();
        String staus = "审核通过";
        if (baiseeLeaveRecord.getStatus() == '3') {
            staus = "拒绝";
        }
        if (baiseeLeaveRecord.getStatus() == '4') {
            staus = "取消";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
        String check_time = dateFormat.format(baiseeLeaveRecord.getCheck_time());
        PropertiesUtil p = new PropertiesUtil("WeChat.properties");
        String template_id = p.get("stu_temlate_id");
        JSONObject jsonObject = new JSONObject();
        //touser  openId
        String appId = p.get("appID");
        String url = p.get("url");
        jsonObject.put("url", "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appId+"&redirect_uri=http%3a%2f%2f"+url+"%2fleave&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
        jsonObject.put("touser", openId);
        //template_id 模板ID
        jsonObject.put("template_id", template_id);
        JSONObject data = new JSONObject();
        JSONObject first = new JSONObject();
        first.put("value", "您好，您的审核结果提醒。");
        first.put("color", "#173177");
        data.put("first", first);
        JSONObject keyword1 = new JSONObject();
        keyword1.put("value", baiseeLeaveRecord.getStudent_name());
        keyword1.put("color", "#173177");
        data.put("keyword1", keyword1);
        JSONObject keyword2 = new JSONObject();
        keyword2.put("value", "");
        keyword2.put("color", "#173177");
        data.put("keyword2", keyword2);
        JSONObject keyword3 = new JSONObject();
        keyword3.put("value", dateFormat.format(baiseeLeaveRecord.getStart_time()) + " -- " + dateFormat.format(baiseeLeaveRecord.getEnd_time()));
        keyword3.put("color", "#173177");
        data.put("keyword3", keyword3);
        JSONObject keyword4 = new JSONObject();
        keyword4.put("value", dateFormat.format(baiseeLeaveRecord.getCreate_time()));
        keyword4.put("color", "#173177");
        data.put("keyword4", keyword4);
        JSONObject keyword5 = new JSONObject();
        keyword5.put("value", staus);
        keyword5.put("color", "#173177");
        data.put("keyword5", keyword5);
        JSONObject remark = new JSONObject();
        if (baiseeLeaveRecord.getStatus()=='2') {
            remark.put("value", "审核人：" + baiseeLeaveRecord.getCheck_user_name());
        } else {
            remark.put("value", "审核人：" + baiseeLeaveRecord.getCheck_user_name() + "，拒绝原因：" + baiseeLeaveRecord.getReject_reason());
        }
        remark.put("color", "#173177");
        data.put("remark", remark);
        jsonObject.put("data", data);
        System.err.println(jsonObject);
        HttpUtil.doPostJson("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + access_token, jsonObject);
    }





    public static void sendTemplateInfo(String openId, BaiseeNoticeStaff baiseeNoticeStaff,String access_token) throws Exception {
//        access_token

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH");
        PropertiesUtil p = new PropertiesUtil("WeChat.properties");
        String template_id = baiseeNoticeStaff.getStu_temlate_id();
        JSONObject jsonObject = new JSONObject();
        //touser  openId
        String appId = p.get("appID");
        String url = p.get("url");
        jsonObject.put("touser", openId);
        //template_id 模板ID
        jsonObject.put("template_id", template_id);
        JSONObject data = new JSONObject();
        JSONObject first = new JSONObject();
        first.put("value", baiseeNoticeStaff.getT_title());
        first.put("color", "#173177");
        data.put("first", first);
        JSONObject keyword5 = new JSONObject();
        keyword5.put("value", baiseeNoticeStaff.getT_content());
        keyword5.put("color", "#173177");
        data.put("keyword5", keyword5);
        jsonObject.put("data", data);
        String result = HttpUtil.doPostJson("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + access_token, jsonObject);
        System.err.println(result);
    }
}

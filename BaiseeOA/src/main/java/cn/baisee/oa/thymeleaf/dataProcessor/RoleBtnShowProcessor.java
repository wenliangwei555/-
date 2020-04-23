package cn.baisee.oa.thymeleaf.dataProcessor;

import java.util.List;

import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;
import org.thymeleaf.processor.attr.AbstractConditionalVisibilityAttrProcessor;

import cn.baisee.oa.model.BaiseeMenu;
import cn.baisee.oa.model.SessionUserInfo;
import cn.baisee.oa.thymeleaf.utils.OaDialectUtil;
import cn.baisee.oa.utils.ExtCacheUtils;
import cn.baisee.oa.utils.SessionUtil;
import cn.baisee.oa.utils.StringUtil;

/**
 * 页面权限控制{
 * 		进行按钮显隐的控制
 *  	在原按钮中 加入 baisee:ctrl="BJGL_BJSC" 即可控制
 *     其中“”里面的是 menu表中的 menu_code
 *  
 * }
 * 
 * @author Administrator
 * 
 */
public class RoleBtnShowProcessor extends AbstractConditionalVisibilityAttrProcessor {

	public RoleBtnShowProcessor() {
		this("ctrl");
	}

	protected RoleBtnShowProcessor(String attributeName) {
		super(attributeName);
	}

	@Override
	public int getPrecedence() {
		return 0;
	}

	@Override
	protected boolean isVisible(Arguments arguments, Element element, String attributeName) {

		String roleCode = element.getAttributeValue(attributeName);
		if (StringUtil.isEmpty(roleCode)) {
			return false;
		}

		if (roleCode.indexOf("{") > -1) {
			roleCode = OaDialectUtil.getValue(arguments, roleCode);
		}

		SessionUserInfo userInfo = SessionUtil.getUserInfo(OaDialectUtil.getRequest(arguments));
		String userId = userInfo.getUserId();
		if (userInfo == null || StringUtil.isEmpty(userId))
			return false;

		List<BaiseeMenu> menus = ExtCacheUtils.selectMenuByUserId(userId);

		if (menus == null) {
			return false;
		}
		for (BaiseeMenu m : menus) {
			if (m.getMenuCode().equals(roleCode))
				return true;
		}
		return false;
	}

}

package cn.baisee.oa.thymeleaf.dataProcessor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.thymeleaf.Arguments;
import org.thymeleaf.dom.Element;
import org.thymeleaf.dom.Text;
import org.thymeleaf.processor.ProcessorResult;
import org.thymeleaf.processor.attr.AbstractAttrProcessor;

import cn.baisee.oa.constants.AppConstants;
import cn.baisee.oa.model.BaiseeMenu;
import cn.baisee.oa.model.SessionUserInfo;
import cn.baisee.oa.thymeleaf.utils.OaDialectUtil;
import cn.baisee.oa.utils.ExtCacheUtils;
import cn.baisee.oa.utils.SessionUtil;
import cn.baisee.oa.utils.StringUtil;

/**
 * 菜单
 * 
 * @author Administrator
 * 
 */
public class MenuIterator extends AbstractAttrProcessor {
	public MenuIterator() {
		this("menus");
	}

	protected MenuIterator(String attributeName) {
		super(attributeName);
	}

	@Override
	protected ProcessorResult processAttribute(Arguments arguments, Element top, String attributeName) {
		int type = Integer.valueOf(top.getAttributeValue(attributeName));

		switch (type) {
		case 1:
			processMenu(arguments, top);
			break;
		case 2:
			processRole(arguments, top);
			break;
		}

		top.removeAttribute("baisee:menus");
		return ProcessorResult.OK;
	}

	private void processRole(Arguments arguments, Element top) {
		HttpServletRequest request = OaDialectUtil.getRequest(arguments);
		String userId = SessionUtil.getUserInfo(request).getUserId();
		List<BaiseeMenu> menus = ExtCacheUtils.selectMenuByUserId(userId);
		List<BaiseeMenu> treeMenu = ExtCacheUtils.selectAllMenu();
		Text sp = new Text("&nbsp;&nbsp;");
		for (Object o : treeMenu) {
			if (!menus.contains(o))
				continue;
			BaiseeMenu m = (BaiseeMenu) o;
			Element topLi = new Element("li");
			Element topDiv = new Element("div");
			topDiv.setAttribute("style", "margin: -7px 0px 0px 2px;");
			Element topLabel = new Element("label");
			Element topInput = new Element("input");
			topInput.setAttribute("type", "checkbox");
			topInput.setAttribute("value", m.getMenuId());
			topInput.setAttribute("name", "menuIds");
			topInput.setAttribute("pid", m.getpId());
			Element topSpan = new Element("span");
			topSpan.setAttribute("style", "display: inline");
			Text topMenuName = new Text(m.getMenuName());
			topSpan.addChild(topMenuName);
			topLabel.addChild(topInput);
			topLabel.addChild(sp);
			topLabel.addChild(topSpan);
			topDiv.addChild(topLabel);
			topLi.addChild(topDiv);
			Element topUl = new Element("ul");
			topLi.addChild(topUl);
			List<BaiseeMenu> sChilds = m.getChilds();
			if (sChilds != null) {
				for (BaiseeMenu sMenu : sChilds) {
					if (!menus.contains(sMenu))
						continue;
					Element secLi = new Element("li");
					secLi.setAttribute("style", "clear:both;");
					Element secDiv = new Element("div");
					Element secLabel = new Element("label");
					Element secInput = new Element("input");
					secInput.setAttribute("type", "checkbox");
					secInput.setAttribute("value", sMenu.getMenuId());
					secInput.setAttribute("name", "menuIds");
					secInput.setAttribute("pid", sMenu.getpId());
					Element secSpan = new Element("span");
					secSpan.setAttribute("style", "display: inline");
					Text secMenuName = new Text(sMenu.getMenuName());
					secSpan.addChild(secMenuName);
					secLabel.addChild(secInput);
					secLabel.addChild(sp);
					secLabel.addChild(secSpan);
					secDiv.addChild(secLabel);
					secLi.addChild(secDiv);
					Element secUl = new Element("ul");
					secUl.setAttribute("class", "wrap");
					secLi.addChild(secUl);
					List<BaiseeMenu> ssChilds = sMenu.getChilds();
					if (ssChilds != null) {
						for (BaiseeMenu ssMenu : ssChilds) {
							if (!menus.contains(ssMenu))
								continue;
							Element thdLi = new Element("li");
							thdLi.setAttribute("class", "wrap-item");
							Element thdDiv = new Element("div");
							thdDiv.setAttribute("style", "margin: -7px 0px 0px 2px;");
							Element thdLabel = new Element("label");
							Element thdInput = new Element("input");
							thdInput.setAttribute("type", "checkbox");
							thdInput.setAttribute("value", ssMenu.getMenuId());
							thdInput.setAttribute("name", "menuIds");
							thdInput.setAttribute("pid", ssMenu.getpId());
							Element thdSpan = new Element("span");
							thdSpan.setAttribute("style", "display: inline");
							Text thdMenuName = new Text(ssMenu.getMenuName());
							thdSpan.addChild(thdMenuName);
							thdLabel.addChild(thdInput);
							thdLabel.addChild(sp);
							thdLabel.addChild(thdSpan);
							thdDiv.addChild(thdLabel);
							thdLi.addChild(thdDiv);
							secUl.addChild(thdLi);
						}
					}
					topUl.addChild(secLi);
				}
			}
			top.addChild(topLi);
		}
	}

	private void processMenu(Arguments arguments, Element top) {
		HttpServletRequest request = OaDialectUtil.getRequest(arguments);
		// 防止session为空的时候 的空指针
		HttpServletResponse response = OaDialectUtil.getResponse(arguments);
		SessionUserInfo info = SessionUtil.getUserInfo(request);
		if (info == null) {
			try {
				response.setHeader("content-type", "text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				StringBuilder builder = new StringBuilder();
				builder.append("<script type=\"text/javascript\" charset=\"UTF-8\" src=\"")
						.append(request.getContextPath()).append("/js/jquery-1.11.3.min.js\">").append("</script>");
				builder.append("<script type=\"text/javascript\" charset=\"UTF-8\">");
				builder.append("$(function(){window.top.location.href='").append(request.getContextPath())
						.append("/welcome.ht';});");
				builder.append("</script>");
				out.print(builder.toString());
				out.close();
				return;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		String userId = info.getUserId();
		List<BaiseeMenu> menus = ExtCacheUtils.selectMenuByUserId(userId);
		List<BaiseeMenu> cacheMenu = ExtCacheUtils.selectAllMenu();
		String curMenuPCode = SessionUtil.getString(OaDialectUtil.getRequest(arguments), AppConstants.CUR_MENU_PCODE);
		String curMenuCode = SessionUtil.getString(OaDialectUtil.getRequest(arguments), AppConstants.CUR_MENU_CODE);
		// 设置首页
		addHomeMenu(top, curMenuPCode, arguments);
		for (Object o : cacheMenu) {
			if (!menus.contains(o))
				continue;
			
			
			
			
//			<li class="nav-item active"> 
//          <a href="index.html"><i class="mdi mdi-home"></i> 后台首页</a> 
//          </li>
			
//			<li class="nav-item nav-item-has-subnav">
//          <a href="javascript:void(0)"><i class="mdi mdi-palette"></i> UI 元素</a>
//          <ul class="nav nav-subnav">
//            <li> <a href="lyear_ui_buttons.html">按钮</a> </li>
//            <li> <a href="lyear_ui_cards.html">卡片</a> </li>
//            <li> <a href="lyear_ui_grid.html">格栅</a> </li>
//          </ul>
//        </li>
			
			BaiseeMenu m = (BaiseeMenu) o;
			Element topLi = new Element("li");
			
			
			if (curMenuPCode.equals(m.getMenuCode())) {
				topLi.setAttribute("class", "nav-item nav-item-has-subnav active open");
			}else {
				topLi.setAttribute("class", "nav-item nav-item-has-subnav");
			}
			
			Element topA = new Element("a");
			topA.setAttribute("href", "javascript:void(0)");
			Element i = new Element("i");
			if(StringUtils.isNotBlank(m.getMenuIcon())) {
				i.setAttribute("class", String.format("mdi %s", m.getMenuIcon()));
			}else {
				i.setAttribute("class", "mdi mdi-home");
			}
			
			
			topA.addChild(i);
			Text topMenuName = new Text(m.getMenuName());
			topA.addChild(topMenuName);
			
			Element subUl = new Element("ul");
			subUl.setAttribute("class", "nav nav-subnav");
			
			List<BaiseeMenu> sChilds = m.getChilds();
			if (sChilds != null) {
				
				for (BaiseeMenu sMenu : sChilds) {
					if (!menus.contains(sMenu)) {
						continue;
					}
					Element subLi = new Element("li");
					if (StringUtil.isNotEmpty(curMenuCode) && curMenuCode.equals(sMenu.getMenuCode())) {
						subLi.setAttribute("class", "active");
					}
					Element subA = new Element("a");
					subA.setAttribute("href", OaDialectUtil.getRequest(arguments).getContextPath() + "/" + sMenu.getMenuUrl());
					Text subMenuName = new Text(sMenu.getMenuName());
					subA.addChild(subMenuName);
					
					subLi.addChild(subA);
					
					subUl.addChild(subLi);
					
				}
			}
			topLi.addChild(topA);
			topLi.addChild(subUl);
			top.addChild(topLi);
		}
	}

	private void addHomeMenu(Element top, String curMenuPCode, Arguments arguments) {
//		<li class="nav-item active"> 
//      <a href="index.html"><i class="mdi mdi-home"></i> 后台首页</a> 
//      </li>
		
		
		String url = OaDialectUtil.getRequest(arguments).getContextPath() + "/" + "indexpage.ht";
		Element li = new Element("li");
		li.setAttribute("class", "nav-item");
		
		StringBuffer classStr = new StringBuffer("nav-item");
		if (AppConstants.HOME_MENU_CODE.equals(curMenuPCode)) {
			classStr.append(" active");
		}
		li.setAttribute("class", classStr.toString());
		Element a = new Element("a");
		a.setAttribute("href", url);
		
		Element i = new Element("i");
		i.setAttribute("class", "mdi mdi-home");
		a.addChild(i);
		Text menuName = new Text("后台首页");
		a.addChild(menuName);
		li.addChild(a);
		top.addChild(li);
	}

	@Override
	public int getPrecedence() {
		return 0;
	}

}

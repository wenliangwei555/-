package cn.baisee.oa.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import cn.baisee.oa.annotation.Login;
import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.model.BaiseeMenu;
import cn.baisee.oa.service.MenuService;

/**
 * 菜单管理
 */
@Controller
@RequestMapping
public class IndexController {

    @Autowired
    private MenuService menuService;

    /**
     * 首页
     *
     * @param request
     * @return
     */
    @RequestMapping("index")
    @Role(value = "KQJL_JLCK")
    public String toList(HttpServletRequest request) {
        return "index";
    }

    /**
     * 个人信息
     *
     * @param request
     * @param
     * @return
     */
    @RequestMapping("profile")
    @Login(ignore = true)
    @Role(ignore = true)
    public String profile(HttpServletRequest request) {
        return "profile";
    }

    /**
     * 修改密码
     *
     * @param request
     * @return
     */
    @RequestMapping("modifyPwd")
    @Login(ignore = true)
    @Role(ignore = true)
    public String modifyPwd(HttpServletRequest request) {
        return "modifyPwd";
    }

    /**
     * 菜单列表
     *
     * @param request
     * @return
     */
    @RequestMapping("sys/menu/list")
    @Login(ignore = true)
    @Role(ignore = true)
    public String menuList(HttpServletRequest request, ModelAndView model) {

        return "sys/menu/menu_list";
    }

    /**
     * 跳转至图标页面
     *
     * @param request
     * @param mv
     * @return
     */
    @RequestMapping("sys/menu/icons")
    @Login(ignore = true)
    @Role(ignore = true)
    public ModelAndView icons(HttpServletRequest request, ModelAndView mv) {
        mv.setViewName("sys/menu/menu_icons");
        return mv;
    }

    /**
     * 菜单新增页面
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("sys/menu/add")
    @Login(ignore = true)
    @Role(ignore = true)
    public String menuAdd(HttpServletRequest request, String parentId, String menuName, String menuCode, String menuIcon, String menuUrl, String orderNum, String pId, Model model) {
        model.addAttribute("parentId", parentId);
        model.addAttribute("menuName", menuName);
        model.addAttribute("menuCode", menuCode);
        model.addAttribute("menuIcon", menuIcon);
        model.addAttribute("menuUrl", menuUrl);
        model.addAttribute("orderNum", orderNum);
        model.addAttribute("pId", pId);
        return "sys/menu/menu_add";
    }

    /**
     * 按钮修改页面
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("sys/menu/modify")
    @Login(ignore = true)
    @Role(ignore = true)
    public String menuModify(HttpServletRequest request, String menuId, Model model) {
        BaiseeMenu menu = menuService.getMenusByMenuId(menuId);
        model.addAttribute("info", menu);
        return "sys/menu/menu_modify";
    }

    /**
     * 保存菜单
     *
     * @param request
     * @param menu
     * @return
     */
    @ResponseBody
    @RequestMapping("sys/menu/save")
    @Login(ignore = true)
    @Role(ignore = true)
    public String menuSave(HttpServletRequest request, BaiseeMenu menu) {


        return "ok";
    }

    /**
     * @return
     */
    @ResponseBody
    @RequestMapping("sys/menu/data")
    @Role(ignore = true)
    @Login(ignore = true)
    public List<BaiseeMenu> getCurrUserNoticeList(String menuName) {
        return menuService.selectAll(menuName);
    }


    /**
     * 菜单添加以及修改
     *
     * @param request
     * @param model
     * @param menu
     * @return
     */
    @RequestMapping(value = "sys/menu/SaveAndModify", produces = {"text/html;charset=UTF-8;", "application/json;"})
    @Role(ignore = true)
    @Login(ignore = true)
    @ResponseBody
    public String SaveAndModify(HttpServletRequest request, Model model, BaiseeMenu menu) {
        int row = 0;
        if (menu.getMenuId() != null && !menu.getMenuId().equals("")) {
            row = menuService.Modify(request, model, menu);
        } else {
            long time = new Date().getTime();
            //long time = System.currentTimeMillis();
            String a = time + "";
            String menuid = menu.getpId() + a.substring(a.length() - 2, a.length());
            menu.setMenuId(menuid);
            row = menuService.Save(request, model, menu);
        }
        if (row > 0) {
            return "操作成功";
        } else {
            return "操作失败";
        }
    }

    /**
     * 删除
     *
     * @param menu_id
     * @return
     */
    @RequestMapping(value = "sys/menu/del", produces = {"text/html;charset=UTF-8;", "application/json;"})
    @Role(ignore = true)
    @Login(ignore = true)
    @ResponseBody
    public String delete(String menu_id) {
        int row = 0;
        if (menu_id != null && !menu_id.equals("")) {
            row = menuService.delete(menu_id);
        }
        if (row > 0) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }
}




























package cn.baisee.oa.service;

import java.util.List;

import cn.baisee.oa.model.BaiseeMenu;
import org.springframework.ui.Model;
import com.github.pagehelper.PageInfo;
import javax.servlet.http.HttpServletRequest;

public interface MenuService {

    BaiseeMenu getMenusByMenuId(String menuId);

    List<BaiseeMenu> getMenusByUserId(String userId);

    List<BaiseeMenu> selectChilds(String menuId);

    /**
     * 模糊查询 菜单权限
     *
     * @param name 输入的菜单名字
     * @return
     */
    List<BaiseeMenu> selectAll(String name);

    Integer Save(HttpServletRequest request, Model model, BaiseeMenu menu);

    Integer Modify(HttpServletRequest request, Model model, BaiseeMenu menu);

    Integer delete(String menu_id);
}

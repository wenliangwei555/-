package cn.baisee.oa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.baisee.oa.utils.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.baisee.oa.dao.baisee.BaiseeMenuMapper;
import cn.baisee.oa.model.BaiseeMenu;
import cn.baisee.oa.service.MenuService;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

@Service
class MenuServiceiceImpl implements MenuService {

    private static BaiseeMenuMapper baiseeMenuMapper;

    @Autowired
    public MenuServiceiceImpl(BaiseeMenuMapper baiseeMenuMappe) {
        baiseeMenuMapper = baiseeMenuMappe;
    }


    @Override
    public List<BaiseeMenu> getMenusByUserId(String userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        return baiseeMenuMapper.selectMenuByCondition(map);
    }

    @Override
    public List<BaiseeMenu> selectChilds(String menuId) {
        List<BaiseeMenu> menus = baiseeMenuMapper.selectMenuByPid(menuId);
        if (CollectionUtils.isNotEmpty(menus)) {
            for (BaiseeMenu m : menus) {
                m.setChilds(selectChilds(m.getMenuId()));
            }
        }
        return menus;
    }

    /**
     * 参数未null  全查
     *
     * @param name 输入的菜单名字
     * @return
     */
    @Override
    public List<BaiseeMenu> selectAll(String name) {
        if ("".equals(name) || null == name) {
            return baiseeMenuMapper.selectAll(null, null);
        }
        //模糊搜索
        return baiseeMenuMapper.selectbaiseeMenubyname(StringUtil.injectPrevent(name));
    }


    @Override
    public Integer Save(HttpServletRequest request, Model model, BaiseeMenu menu) {
        int row = baiseeMenuMapper.Save(menu);
        return row;
    }

    @Override
    public Integer Modify(HttpServletRequest request, Model model, BaiseeMenu menu) {
        int row = baiseeMenuMapper.Modify(menu);
        return row;
    }

    @Override
    public Integer delete(String menu_id) {
        int row = baiseeMenuMapper.delete(menu_id);
        return row;
    }


    @Override
    public BaiseeMenu getMenusByMenuId(String menuId) {
        // TODO Auto-generated method stub
        return baiseeMenuMapper.selectMenusByMenuId(menuId);
    }

}

package cn.baisee.oa.dao.baisee;

import java.util.List;
import java.util.Map;

import cn.baisee.oa.core.datasource.Datasource;
import cn.baisee.oa.core.datasource.DatasourceTypes;
import cn.baisee.oa.model.BaiseeMenu;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

@Datasource(DatasourceTypes.OA)
public interface BaiseeMenuMapper {
    /**
     * 查询子菜单
     * @param menuId
     * @return
     */
    List<BaiseeMenu> selectbyid(String menuId);
    /**
     *使用 name 模糊查询
     * @param name
     */
    List<BaiseeMenu> selectbaiseeMenubyname(String name);

    List<BaiseeMenu> selectMenuByCondition(Map<String, Object> map);

    List<BaiseeMenu> selectMenuByPid(String parentId);

    /**
     * 模糊查询 菜单权限
     *
     * @param name   输入的菜单名字
     * @param menuid 菜单id 可能未null
     * @return
     */
    List<BaiseeMenu> selectAll(String name, String menuid);

    BaiseeMenu selectMenusByMenuId(String menuId);

    Integer Save(BaiseeMenu menu);

    Integer Modify(BaiseeMenu menu);

    Integer delete(String menu_id);



}

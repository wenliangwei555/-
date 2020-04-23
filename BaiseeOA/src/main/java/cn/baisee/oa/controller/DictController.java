package cn.baisee.oa.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.annotation.Login;
import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.model.Area;
import cn.baisee.oa.model.City;
import cn.baisee.oa.model.IcipBusDict;
import cn.baisee.oa.model.Province;
import cn.baisee.oa.model.ResponseResult;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.DictService;
import cn.baisee.oa.utils.ParamUtils;


@Controller
@RequestMapping("/dict")
public class DictController {
    @Autowired
    private DictService dictService;


    /**
     * 修改和添加
     *
     * @return
     */
    @RequestMapping("/saveDict.ht")
    public ModelAndView saveDict() {
        ModelAndView model = new ModelAndView("success");
        return model;
    }

    /**
     * 跳转到 修改页面
     *
     * @return
     */
    @RequestMapping("/toUpdateDict")
    @Role(value = "ZDGL_ZDXG")
    public ModelAndView toUpdateDict(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("busdict/busdictInfo");
        String dictId = ParamUtils.getParameter(request, "dictId");
        IcipBusDict dict = dictService.getDictById(dictId);
        model.addObject("info", dict);
        return model;
    }

    /**
     * 修改字典
     *
     * @param request
     * @param dict
     * @return
     */
    @RequestMapping("updateDict.ht")
    @Role(value = "ZDGL_ZDXG")
    public String updateDict(HttpServletRequest request, IcipBusDict dict) {
		/*String dictId=ParamUtils.getParameter(request, "dictId");
		String dictName=ParamUtils.getParameter(request, "dictName");
		String dictValue=ParamUtils.getParameter(request, "dictVvalue");
		String dictDestination=ParamUtils.getParameter(request, "dictDestination");
		String remark=ParamUtils.getParameter(request, "remark");*/
        dictService.updateDict(dict);
        return "forward:getDictList.ht";
    }

    /**
     * 跳转到字典查询页面
     *
     * @return
     */
    @RequestMapping("/getDictList.ht")
    @Role(value = "ZDGL_ZDCK")
    public ModelAndView getDictList(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("busdict/busdictList");
        int pageNum = ParamUtils.getPageNumParameters(request);
        int pageSize = ParamUtils.getPageSizeParameters(request);
        PageInfo<IcipBusDict> info = dictService.getDictList(pageNum, pageSize);
        model.addObject("pageResult", info);
        return model;
    }

    /**
     * 批量删除
     *
     * @param request
     * @return
     */
    @RequestMapping("/delDict.ht")
    @Role(value = "ZDGL")
    public String delDict(HttpServletRequest request) {
        String[] dictIds = ParamUtils.getParameters(request, "ids");
        dictService.delDict(dictIds);
        return "forward:getDictList.ht";
    }

    @RequestMapping("checkDict.ht")
    public ModelAndView checkDict(IcipBusDict dict) {
        ModelAndView model = new ModelAndView("busdict/busdictInfo");
        model.addObject("info", dictService.checkDict(dict));
        return model;
    }

    /**
     * 获取所以的省份
     *
     * @return
     */
    @RequestMapping("/getProvince.ht")
    @Login(ignore = true)
    @Role(ignore = true)
    @ResponseBody
    public List<Province> getProvince() {
        return dictService.getProvince();
    }

    /**
     * 获取市区
     *
     * @param provinceCode
     * @return
     */
    @RequestMapping("/getCity.ht")
    @Login(ignore = true)
    @Role(ignore = true)
    @ResponseBody
    public List<City> getCity(String provinceCode) {
        return dictService.getCity(provinceCode);
    }

    //获取县区信息
    @RequestMapping("/getArea.ht")
    @Login(ignore = true)
    @Role(ignore = true)
    @ResponseBody
    public List<Area> getArea(String provinceCode) {
        return dictService.getArea(provinceCode);
    }

}

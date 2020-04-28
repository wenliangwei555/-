package cn.baisee.oa.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.model.goods.BaiseeGoodsType;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeGoodsService;
import cn.baisee.oa.utils.ParamUtils;

@Controller
@RequestMapping("goods")
public class BaiseeGoodsTypeController {
	@Autowired
	public BaiseeGoodsService baiseeGoodsService;
	
	/**
	    * 显示 库存物品类型  详情并分页
	 * @author zzy
	 * @param request
	 * @param respone
	 * @param typeName 分类名称 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toGoodsTypeList")
	@Role("WPKC_FLGL")
	public ModelAndView toGoodsTypeList(HttpServletRequest request,HttpServletResponse respone,String typeName) {
		
		Integer pageNum=ParamUtils.getPageNumParameters(request);
		Integer pageSize=ParamUtils.getPageSizeParameters(request);
		PageInfo<BaiseeGoodsType> pageInfo = baiseeGoodsService.findAllGoodsType(pageNum, pageSize, typeName);
		ModelAndView mv = new ModelAndView("goods/goods_type_list");
		mv.addObject("pageResult",pageInfo);
		mv.addObject("typeName",typeName);
		return mv;
		
	}
	
	/***
	   *  添加库存分类信息
	 * @author zzy
	 * @param request
	 * @param respone
	 * @param typeName  要添加的分类名称
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addGoodsType")
	@Role(value="WPKC_FLGL") 
	public ModelAndView addGoodsType(HttpServletRequest request,HttpServletResponse respone,String typeName ) throws Exception{
		baiseeGoodsService.addGoodsType(typeName);
			
		return toGoodsTypeList(request, respone,null);
	}
	
	
	/**
	 *去往添加库存分类页面
	 *@author zzy
	 * @return
	 */
	@RequestMapping("/addGoodsTypeView")
	@Role(value="WPKC_FLGL")
	public ModelAndView addGoodsTypeView(){
		ModelAndView mav=new ModelAndView("goods/goods_type_add");
		return mav;
	}
	

	/**
	 * 查出要修改的该条数据并返回
	 * @author zzy
	 * @param typeId  分类id
	 * @return
	 */
	@RequestMapping("/toOneGoodsType")
	@Role("WPKC_FLGL")
	public ModelAndView getOneGoodsType(String typeId) {
		
		BaiseeGoodsType bgt = baiseeGoodsService.selectOneGoodsTypeById(typeId);
		ModelAndView mv = new ModelAndView("goods/goods_type_update");
		mv.addObject("bgt", bgt);
		return mv;
	}
	
	
	/**
	   * 修改分类名称
	 */
	@RequestMapping("/updateGoodsTypeName")
	@Role("WPKC_FLGL")
	public ModelAndView updateGoodsTypeName(HttpServletRequest request,HttpServletResponse respone,BaiseeGoodsType baiseeGoodsType) {
		
		baiseeGoodsService.updateGoodsType(baiseeGoodsType);
		return toGoodsTypeList(request, respone, null);
	}
	
	/**
	 *  删除商品分类
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delGoodsType")
	@Role("WPKC_FLGL")
	public ModelAndView removeGoodsTyp(HttpServletRequest request,HttpServletResponse respone,String [] ids) {
		
		baiseeGoodsService.deleteGoodsType(ids);
		return toGoodsTypeList(request, respone, null);
	}
	
	
	
}

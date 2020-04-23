package cn.baisee.oa.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.model.BaiseeUser;
import cn.baisee.oa.model.goods.BaiseeGoodsRepertory;
import cn.baisee.oa.model.goods.BaiseeGoodsType;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeGoodsService;
import cn.baisee.oa.service.BiseeRepertoryService;
import cn.baisee.oa.utils.ParamUtils;
import cn.baisee.oa.utils.SessionUtil;
/**
 * 
 * @author zzy
 *
 */
@Controller
@RequestMapping("repertory")
public class BiseeRepertoryController {
	@Autowired
	private BiseeRepertoryService biseeRepertoryService;
	@Autowired
	private BaiseeGoodsService baiseeGoodsService;
	
	/**
	    * 显示 库存物品&分类  详情并分页
	 * @author zzy
	 * @param request
	 * @param respone
	 * @param typeId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/toRepertoryList")
	@Role(value="WPKC")
	public ModelAndView toRepertoryList(HttpServletRequest request,HttpServletResponse respone,String goodsName,String typeId)  {
		List<BaiseeGoodsType> list = baiseeGoodsService.getAllGoodsTypeList();//获取物品分类的信息
		Integer pageNum=ParamUtils.getPageNumParameters(request);
		Integer pageSize=ParamUtils.getPageSizeParameters(request);
		if("-1".equals(typeId)){
			typeId=null;
		}
		PageInfo<BaiseeGoodsRepertory> pageInfo = biseeRepertoryService.getGoodsRepertoryList(pageNum, pageSize,goodsName,typeId);
		ModelAndView mv = new ModelAndView("goods/repertory_list");
		mv.addObject("pageResult",pageInfo);
		mv.addObject("typeList", list);
		mv.addObject("goodsName", goodsName);
		return mv;
	}
	/**
	   * 根据物品ID查出要修改的该条数据并返回
	 * @author zzy
	 * @param goodsId  物品id
	 * @return
	 */
	@RequestMapping("/toOneRepertoryById")
	@Role(value="WPKC_CKWP") 
	public ModelAndView toOneRepertoryById(String goodsId) {
		
		BaiseeGoodsRepertory bgr = biseeRepertoryService.getOneGodsRepertory(goodsId);
		ModelAndView mv = new ModelAndView("goods/repertory_update");
		mv.addObject("bgr",bgr);
		mv.addObject("id", bgr.getGoodsId());
		return mv;
	}
	/***
	 *   修改物品的库存数量
	 * @author zzy
	 * @param request
	 * @param baiseeGoodsRepertory  要修改物品的参数信息  用对象接收
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/updateRepertoryNum")
	@Role(value="WPKC_XGKC") 
	public ModelAndView updateRepertoryNum(HttpServletRequest request,HttpServletResponse response,BaiseeGoodsRepertory baiseeGoodsRepertory)  {
		biseeRepertoryService.updateOneGodsRepertory(baiseeGoodsRepertory);
		return toRepertoryList(request, response,null,"-1");
	}
	

	/***
	 *  执行添加库存物品
	 * @author zzy
	 * @param request
	 * @param respone
	 * @param baiseeGoodsRepertory   添加物品的参数信息  用对象接收
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addRepertory")
	@Role(value="WPKC_XZKC") 
	public ModelAndView addRepertory(HttpServletRequest request,HttpServletResponse respone,BaiseeGoodsRepertory baiseeGoodsRepertory)  {
		//获取当前用户名称
		BaiseeUser currUser = (BaiseeUser)SessionUtil.getUserInfo(request);
		String updatePerson = currUser.getUserName();
		baiseeGoodsRepertory.setUpdatePerson(updatePerson);
		
		biseeRepertoryService.addOneGodsRepertory(baiseeGoodsRepertory);		
		return toRepertoryList(request, respone,null,"-1");
	}
	
	
	/**
	 *去往添加库存物品页面
	 *@author zzy
	 * @return
	 */
	@RequestMapping("/toAddRepertory")
	@Role(value="WPKC_XZKC")
	public ModelAndView toAddDic(){
		
		List<BaiseeGoodsType> list = baiseeGoodsService.getAllGoodsTypeList();
		ModelAndView mav=new ModelAndView("goods/repertory_add");
		mav.addObject("list", list);
		return mav;
	}
	
	
	/**
	 * 批量删除库存物品
	 * @param ids  所有选中要删除物品的id
	 * @return
	 * @throws Exception
	 * @author zzy
	 */
	@RequestMapping("/deleteRepertory")
	@Role(value="WPKC_SCKC")
	public ModelAndView delDict(HttpServletRequest request,HttpServletResponse respone,String[] ids)  {
		
		biseeRepertoryService.delGodsRepertory(ids);
		return toRepertoryList(request,respone,null,null);
	}
	
	
	
}

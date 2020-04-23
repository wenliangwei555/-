package cn.baisee.oa.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.baisee.oa.annotation.Role;
import cn.baisee.oa.model.BaiseeCardBill;
import cn.baisee.oa.model.BaiseeCardRyxx;
import cn.baisee.oa.page.pagehelper.PageInfo;
import cn.baisee.oa.service.BaiseeCardBillService;
import cn.baisee.oa.service.BaiseeCardRyxxServixe;

@Controller
@RequestMapping("/card")
public class BaiseeCardInfoController {
	@Autowired
	private BaiseeCardRyxxServixe cardRyxxServixe;
	@Autowired
	private BaiseeCardBillService cardBillService;
	@Role(value="CARD_LIST")
	@RequestMapping("/cardView.ht")
	public ModelAndView cardView(ModelAndView mv,@RequestParam(defaultValue="1")int pageNum,@RequestParam(defaultValue="10")int pageSize,String name) {
		mv.setViewName("card/cardAdmin");
		PageInfo<BaiseeCardRyxx>  page = cardRyxxServixe.getCardAll(pageNum, pageSize,name);
		mv.addObject("pageResult",page);
		mv.addObject("name",name);
		return mv;
	}
	
	@Role(value="CARD_LIST")
	@RequestMapping("/getmoney.ht")
	public ModelAndView money(@RequestParam(defaultValue="1")int pageNum,@RequestParam(defaultValue="10")int pageSize,
			String cardNum,String type,String name,String time) {
		if(type != null) {
			if(type.equals("1")) {
				type = "增款";
			}else if(type.equals("0")){
				type = "消费";
			}else {
				type = null;
			}
		}else {
			type = null;
		}
		ModelAndView mv = new ModelAndView();
		Map<String, String> map = new HashMap<String, String>();
		map.put("cardNum", cardNum);
		map.put("type", type);
		map.put("time", time);
		PageInfo<BaiseeCardBill> page = cardBillService.getMoney(map,pageNum,pageSize);
		for (BaiseeCardBill baiseeCardBill : page.getList()) {
			baiseeCardBill.setName(name);
		}
		mv.addObject("pageResult", page);
		mv.addObject("type", type);
		mv.addObject("cardNum", cardNum);
		mv.addObject("time", time);
		mv.setViewName("card/cardMoney");
		return mv;
	}
}

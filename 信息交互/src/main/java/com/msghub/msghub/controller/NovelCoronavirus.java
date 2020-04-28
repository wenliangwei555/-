package com.msghub.msghub.controller;

import com.msghub.msghub.config.Constant;
import com.msghub.msghub.model.Parameter;
import com.msghub.msghub.model.nationwide.Complete;
import com.msghub.msghub.model.nationwide.Data;
import com.msghub.msghub.utils.MyUtil;
import net.sf.json.JSONObject;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author 温莨
 * @Date 2020/2/20 18:40
 * @Version 1.0
 */
@RestController
public class NovelCoronavirus {
    /**
     * @return
     */
    @RequestMapping("view")
    public Object view() throws Exception {
        return new ModelAndView("epidemic");
    }

    /**
     * @return
     */
    @RequestMapping("nationwide")
    public static Object getnationwide() throws Exception {
        String s = MyUtil.httpsRequest(Constant.GET_PROVINCE_URL);
        System.out.println(s);
        return  s;
    }

    public static void main(String[] args)  throws Exception {
        try {
            Object getnationwide = getnationwide();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

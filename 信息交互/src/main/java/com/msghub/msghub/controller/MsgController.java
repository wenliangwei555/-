package com.msghub.msghub.controller;


import com.alibaba.fastjson.JSONObject;
import com.msghub.msghub.config.CheckResult;
import com.msghub.msghub.exception.AppException;
import com.msghub.msghub.model.Result;
import com.msghub.msghub.service.Msgservice;
import com.msghub.msghub.model.Parameter;


import com.msghub.msghub.strategy.CellphoneMsg;
import com.msghub.msghub.strategy.Mail;
import com.msghub.msghub.strategy.WeChat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

/**
 * @Author 温莨
 * @Date 2020/1/31 16:02
 * @Version 1.0
 */

@RestController
public class MsgController {

    @Autowired
    private Msgservice service;


    /**
     * @param parameter
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/msg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Object sendMessge(Parameter parameter) throws Exception {
        return service.setsendMessge(parameter);
    }


    /**
     * 服务调用
     *
     * @param
     */
    @RequestMapping("/test")
    public Object test() throws Exception {
        return "访问成功";
    }

    /**
     * 服务调用
     *
     * @param req
     * @return
     * @throws Exception
     */
    @CrossOrigin  //跨域
    @RequestMapping(value = "/tmsg", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Object testsendMessge(HttpServletRequest req) throws Exception {
        StringBuffer s = new StringBuffer();
        BufferedReader read = req.getReader();
        String input = null;
        //读取发过来的信息
        while ((input = read.readLine()) != null) {
            s.append(input);
        }
        Parameter parameter = (Parameter) JSONObject.parseObject(s.toString(), Parameter.class);
        if (parameter.getId() == null || parameter.getId() < 0 || parameter.getId() > 4) {
            return arguments(new Result());
        }
        parameter = (Parameter) setParameter(parameter);
        return sendMessge(parameter);
    }


    /**
     * @param setResult
     * @return
     */
    private Result arguments(Result setResult) {
        setResult.setText(CheckResult.IDNULL.getMsg());
        //关键参数空);
        setResult.setException(new AppException("I10000"));
        return setResult;
    }

    /**
     * @param parameter
     * @return
     */
    public Object setParameter(Parameter parameter) {
        if (1 == parameter.getId()) {
            parameter.setLstrategy(new CellphoneMsg());
        }
        if (2 == parameter.getId()) {
            parameter.setLstrategy(new WeChat());
        }
        if (3 == parameter.getId()) {
            parameter.setLstrategy(new Mail());
        }
        return parameter;
    }
}

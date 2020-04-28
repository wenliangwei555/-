package com.msghub.msghub.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author 温莨
 * @Date 2020/1/31 16:02
 * @Version 1.0
 */

public class GlobalExceptionResolver {
   /* @Override    implements HandlerExceptionResolver
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mv = new ModelAndView();
        //判断不同异常类型，做不同视图跳转
        if (ex instanceof AppException) {
           // mv.setViewName("error1");
            System.out.println("error1");
        }
        if (ex instanceof NullPointerException) {
           // mv.setViewName("error2");
            System.out.println("error2");
        }
        mv.addObject("error", ex.toString());
        return mv;
    }*/
}

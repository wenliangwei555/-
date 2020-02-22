package com.msghub.msghub.service.impl;

import com.msghub.msghub.config.CheckResult;
import com.msghub.msghub.config.ResultCode;
import com.msghub.msghub.exception.AppException;
import com.msghub.msghub.model.Result;
import com.msghub.msghub.strategy.*;
import com.msghub.msghub.service.Msgservice;
import com.msghub.msghub.model.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;


@Service
public class MsgserviceImpl implements Msgservice {





    /**
     * 根绝不同的策略 来发送
     *
     * @param parameter
     */
    @Override
    public Object setsendMessge(Parameter parameter) throws Exception {
        Result setResult = new Result();
        try {
            //参数未空
            if (parameter == null) {
                setResult = arguments(setResult);
                return setResult;
            }
            if (verify(parameter)) {
                setResult = notParameter(setResult);
                return setResult;
            }
            //进来的参数只有 接收人没判断那
            setResult = Context.algorithm(parameter.getLstrategy(), parameter);
            //  setResult = setResult(algorithm);
        } catch (Exception e) {
            throw e;  // 直接抛出，让异常拦截器捕获异常并处理，不能在rtInfo中设置异常信息，即使设置了，调用者也得不到rtInfo中的异常信息
        }
        return setResult;
    }

    /**
     * 参数空
     *
     * @param setResult
     * @return
     */
    private Result arguments(Result setResult) {
        setResult.setText(CheckResult.NULL.getMsg());
        setResult.setException(new AppException("I10001"));  //关键参数空);
        return setResult;
    }

    /**
     * @param setResult
     * @return
     */
    private Result notParameter(Result setResult) {
        setResult.setText(CheckResult.INCOMPLETE.getMsg());
        setResult.setException(new AppException("I10002"));  //关键不完整);
        return setResult;

    }

    /**
     * 必填参数
     *
     * @param parameter
     * @return
     */
    private boolean verify(Parameter parameter) {
        if (parameter.getLstrategy() == null) {//策略不为空
            return true;
        }
        if ("".equals(parameter.getClient_id().trim()) || parameter.getClient_id().trim() == null) { //发送公司
            return true;
        }
        if ("".equals(parameter.getTemplate_id().trim()) || parameter.getTemplate_id().trim() == null) {//模板
            return true;
        }
        if (parameter.getMap() == null || parameter.getMap().size() == 0) {//变量
            return true;
        }
        if (parameter.getReceived() == null || parameter.getReceived().length == 0) {
            return true;
        }
        return false;
    }

    /**
     * 返回信息
     *
     * @param result
     * @return
     *//*
    public Result setResult(Integer result) {
        Map<Integer, Object> map = ResultCode.getMap();
        Set<Integer> objects = map.keySet();
        for (Integer object : objects) {
            if (object == result) {
                Result.setCode(result);
                Result.setText(map.get(result));
                return Result;
            }
        }
        return Result;
    }*/


}

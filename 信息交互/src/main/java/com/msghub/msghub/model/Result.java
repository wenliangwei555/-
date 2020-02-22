package com.msghub.msghub.model;

import org.springframework.stereotype.Component;

/**
 * 返回结果类
 */
@Component
public class Result {
    //返回的信息码
    public Integer code;
    //返回信息文本
    public Object text;

    private Exception exception;// 异常

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getText() {
        return text;
    }

    public void setText(Object text) {
        this.text = text;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }
}

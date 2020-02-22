package com.msghub.msghub.config;

public enum CheckResult {
    IDNULL("请传入发送类型不正确"),
    NULL("请传入参数"),
    INCOMPLETE("参数不完整 不匹配"),
    BUSINESS("业务未开通"),
    SCARCEMONEY("公司余额不足"),
    MESSAGING("邮箱地址错误"),
    SUCCEED("发送成功"),
    RECIPIENTNULL("参数变量空"),
    VARIABLENULL("接收人空"),
    TEMPLATEEROR("模板错误"),
    GONGSINULL("公司找不到"),
    INTERIOR("系统内部错误"),;
    private String msg;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private CheckResult(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }

}

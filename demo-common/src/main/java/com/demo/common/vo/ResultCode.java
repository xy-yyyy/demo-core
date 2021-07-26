package com.demo.common.vo;

/**
 * @Description:
 * @Author: sunYF
 * @Date: 2021/6/17 16:01
 */

public enum ResultCode {
    SUCCESS(200,"SUCCESS"),
    ADD_SUCCESS(200,"添加成功"),
    UPD_SUCCESS(200,"修改成功"),
    SEND_SUCCESS(200,"发送成功"),
    LOGIN_SUCCESS(200, "登录成功"),
    LOGOUT_SUCCESS(200, "退出成功"),
    DOWNLOAD_SUCCESS(200,"下载成功"),
    ID_CARD_AUTH_SUCCESS(200,"实名认证成功"),
    USER_HAD_REGISTER(2010,"该登录名已注册，不能重复注册！"),
    USER_NOT_LOGIN(2001, "用户未登录"),
    USER_NOT_FOUND_ERROR(2005, "未找到当前用户"),
    USER_ACCOUNT_EXPIRED(2002, "账号已过期"),
    USER_CREDENTIALS_ERROR(2003, "密码错误"),
    USER_CREDENTIALS_EXPIRED(2004, "密码过期"),
    ERROR_POSTED_COMM_NULL(2006, "只有发帖人和当前评论人才能回复"),
    ERROR_EXPERT_NULL(2007, "只有专家才能回答问题"),
    ERROR_DEMAND_NULL(2009, "个人账户不能发布需求"),
    ERROR_RECEPT_NULL(2008, "个人账号只承接众筹项目需求"),
    NO_DATA_AVAILABLE(2005,"暂无数据"),
    MAIL_CODE_ALIVE(2006,"验证码已存在，不要重复发送"),
    SUCCESS_CROWDFUNDING(500,"已众筹！"),
    SUCCESS_NO_CROWDFUNDING(500,"已承接！");
    private Integer code;
    private String msg;
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static String getMsg(int key) {
        for (ResultCode c : ResultCode.values()) {
            if (c.getCode() == key) {
                return c.msg;
            }
        }
        return null;
    }
}

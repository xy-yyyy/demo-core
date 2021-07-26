package com.demo.common.vo;

/**
 * @Author: sunYF
 * @Description:
 * @Date: Create in 2021/6/15 11:32
 */
public enum ErrorCode {
    SUCCESS(200,"SUCCESS"),
    ERROR(500, "请求错误"),
    ERROR_PARAM_NULL(500, "参数不能为空"),
    ERROR_ENTITY_NULL(500, "当前需求不存在或已删除"),
    ERROR_STATUS_IDENTICAL(500, "修改失败，状态变更前和变更后不能一致！"),
    ERROR_TYPE_NULL(500, "类型参数不能为空"),
    ERROR_SELECT_EXCEPTION(500, "查询错误"),
    ERROR_FILE(500, "上传失败"),
    ERROR_IDCODE_NULL(500, "主键不能为空"),
    ERROR_DB_EXCEPTION(500, "数据库操作失败"),
    ERROR_MAIL_EXCEPTION(500,"邮件发送失败"),
    ERROR_DUPLICATE_EMAIL(500,"此邮箱已注册"),
    ERROR_MAIL_CODE(500,"邮箱验证码有误"),
    ERROR_MAIL_TO_SEND(500,"验证码未发送或已过期！"),
    ERROR_NO_EMAIl_ADDRESS(500,"邮箱地址为空"),
    ERROR_RESOURCESOLID_NULL(500,"解决方案表ID为空"),
    ERROR_TYPEID_NULL(500,"类型为空"),
    ERROR_DETAIL_NULL(500,"详情不能为空"),
    ERROR_LINK_NULL(500,"链接地址或名称不能为空"),
    ERROR_PICTURE_TYPE(500, "图片格式错误"),
    ERROR_PICTURE(500, "不是图片"),
    ERROR_DOWNLOAD_FAILURE(500,"文件下载失败"),
    ERROR_NO_FILE(500,"文件不存在"),
    ERROR_OVER_SIZE(500,"选择的文件过大")
   ;

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

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsg(int key) {
        for (ErrorCode c : ErrorCode.values()) {
            if (c.getCode() == key) {
                return c.msg;
            }
        }
        return null;
    }
}

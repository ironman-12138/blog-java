package com.xtn.common;

/**
 * @Auther: xCoder
 * @Date: 2021/11/06 10:58
 * @Description: 错误码
 */
public enum ResultCode {
    // 系统编码
    SUCCESS(200, "成功"),
    FAIL(400, "成功"),
    NO_LOGIN(401, "请重新登录"),

    // 业务错误
    NO_COMPLETELY(402, "请填写完整")
    ;

    private final Integer code;
    private final String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    ResultCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}

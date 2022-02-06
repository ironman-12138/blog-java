package com.xtn.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 公共的结果返回类
 * 时间：2021年2月6日 15:31:15
 */

@Data
public class CommonResult implements Serializable {

    private Integer code;     //编码(200/400/...)
    private String message;  //消息
    private Object data;

    //返回成功结果的方法
    public static CommonResult success(Integer code, String message, Object data){
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(code);
        commonResult.setMessage(message);
        commonResult.setData(data);
        return commonResult;
    }

    public static CommonResult success(){
        return success(200, "成功");
    }

    public static CommonResult success(Object data){
        return success(200, "成功", data);
    }

    public static CommonResult success(Integer code, String message){
        return success(code, message, null);
    }

    //返回失败结果的方法
    public static CommonResult failure(Integer code, String message){
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(code);
        commonResult.setMessage(message);
        commonResult.setData(null);
        return commonResult;
    }

    public static CommonResult failure(Integer code, String message, Object data){
        CommonResult commonResult = new CommonResult();
        commonResult.setCode(code);
        commonResult.setMessage(message);
        commonResult.setData(data);
        return commonResult;
    }
}

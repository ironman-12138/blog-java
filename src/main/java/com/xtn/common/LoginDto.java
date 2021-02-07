package com.xtn.common;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 用户登录信息
 * 时间：2021年2月7日 14:00:44
 */
@Data
public class LoginDto {

    @NotBlank(message = "昵称不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;
}

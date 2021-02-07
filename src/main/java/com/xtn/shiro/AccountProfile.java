package com.xtn.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * 封装用户可公开的信息
 * 时间：2021年2月6日 19:54:01
 */

@Data
public class AccountProfile implements Serializable {

    private Long id;
    private String username;
    private String avatar;
    private String email;
}

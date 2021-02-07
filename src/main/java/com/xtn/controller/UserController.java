package com.xtn.controller;

import com.xtn.common.CommonResult;
import com.xtn.domain.User;
import com.xtn.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户控制层
 * 时间：2021年2月6日 14:58:47
 */

@RestController
public class UserController {

    @Resource
    private UserService userService;

    //根据Id查询用户信息
    @RequiresAuthentication  //需要登录验证才能访问
    @GetMapping(value = "/user/select")
    public Object getUserById(long id){
        return userService.getUserById(id);
    }

    //保存用户信息
    @PostMapping(value = "/user/save")
    public Object saveUser(@Validated @RequestBody User user){
        return CommonResult.success(user);
    }
}

package com.xtn.controller;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.xtn.common.CommonResult;
import com.xtn.common.LoginDto;
import com.xtn.domain.User;
import com.xtn.service.UserService;
import com.xtn.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * 账户控制层
 * 时间：2021年2月7日 13:58:37
 */

@RestController
public class AccountController {

    @Resource
    private UserService userService;
    @Resource
    private JwtUtils jwtUtils;

    /**
     * 登录请求处理方法
     * @param loginDto  用户传的登录信息
     * @Validated  对用户传的信息进行验证
     * @RequestBody  接收请求体中传的参数
     */
    @PostMapping(value = "/login")
    public CommonResult login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response){
        //根据用户名查询是否存在用户
        User user = userService.getUserByName(loginDto.getUsername());
        if (user == null){
            //用户名错误
            return CommonResult.failure(400,"用户不存在");
        }
        if (!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))){
            //密码不正确
            return CommonResult.failure(400,"密码不正确");
        }
        //查询执行至此，用户名与密码正确，返回一个token
        String jwt = jwtUtils.generateToken(user.getId());
        //将token信息放入响应头中
        response.setHeader("Authorization",jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");
        //返回一些用户信息
        return CommonResult.success(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail())
                .map()
        );
    }

    // 退出
    @GetMapping("/logout")
    @RequiresAuthentication
    public CommonResult logout() {
        SecurityUtils.getSubject().logout();
        return CommonResult.success();
    }

}

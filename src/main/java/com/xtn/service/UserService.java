package com.xtn.service;

import com.xtn.domain.User;

/**
 * 用户业务层接口
 * 时间：2021年2月6日 14:31:51
 */

public interface UserService {

    //根据Id查询用户信息
    public User getUserById(Long id);

    //根据用户名查询用户信息
    public User getUserByName(String username);
}

package com.xtn.service.impl;

import com.xtn.dao.UserMapper;
import com.xtn.domain.User;
import com.xtn.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户业务层实现类
 * 时间：2021年2月6日 14:32:17
 */

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    //根据Id查询用户信息
    @Override
    public User getUserById(Long id) {
        log.info("查询用户id：" + id);
        return userMapper.selectByPrimaryKey(id);
    }

    //根据用户名查询用户信息
    @Override
    public User getUserByName(String username) {
        return userMapper.selectByName(username);
    }
}

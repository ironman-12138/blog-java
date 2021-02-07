package com.xtn.util;

import com.xtn.shiro.AccountProfile;
import org.apache.shiro.SecurityUtils;

/**
 * 获取Shiro中用户的信息
 * 获取的是AccountRealm中返回的SimpleAuthenticationInfo中的数据(也就是accountProfile)
 */
public class ShiroUtil {
    public static AccountProfile getProfile() {
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}

package com.xlj.shiro.realm;

import com.xlj.bean.User;
import com.xlj.service.UserService;
import com.xlj.util.ApplicationContextUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

/**
 * @Author XuLeJun
 * @Date 2020/7/17 14:52
 *
 * 自定义Realm
 */

public class CustomerRealm extends AuthorizingRealm {
//    @Autowired
//    UserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        if("xiaochen".equals(primaryPrincipal)){
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            simpleAuthorizationInfo.addRole("user");
            simpleAuthorizationInfo.addStringPermission("user:find:*");
            simpleAuthorizationInfo.addStringPermission("user:update:*");
            return simpleAuthorizationInfo;
        }
        return null;
    }


    // 用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("========================");

        // 获取身份信息
        String principal = (String) authenticationToken.getPrincipal();
        // 在工厂工具类中获取service对象
        UserService userService = (UserService) ApplicationContextUtil.getBean("userService");
        User user = userService.findByUserName(principal);

        if(!ObjectUtils.isEmpty(user)){
            return  new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(), ByteSource.Util.bytes(user.getSalt()),this.getName());
        }
        return null;
    }
}

package com.xlj.shiro.realm;

import com.xlj.pojo.Permission;
import com.xlj.pojo.Role;
import com.xlj.pojo.User;
import com.xlj.service.UserService;
import com.xlj.util.ApplicationContextUtil;
import com.xlj.util.SaltBySource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @Author XuLeJun
 * @Date 2020/7/17 14:52
 *
 * 自定义Realm，可以shiro进入个人mysql数据库中获取数据
 */

public class CustomerRealm extends AuthorizingRealm {
    // 用户授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取身份信息
        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
//        System.out.println("调用授权认证："+ primaryPrincipal);
        // 根据身份信息获取角色和权限
        UserService userService = (UserService) ApplicationContextUtil.getBean("userService");
        User user = userService.findRoleByUserName(primaryPrincipal);
        // 授权角色信息
        if (!CollectionUtils.isEmpty(user.getRoleList())) {
            // 授权信息
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            for (Role role : user.getRoleList()) {
                simpleAuthorizationInfo.addRole(role.getName());

                // 权限信息
                List<Permission> permissions = userService.findPermissionByRoleId(role.getId());
                if(!CollectionUtils.isEmpty(permissions)){
                    for (Permission permission : permissions) {
                        simpleAuthorizationInfo.addStringPermission(permission.getName());
                    }
                }
            }
            return simpleAuthorizationInfo;
        }
        return null;
    }


    // 用户认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        System.out.println("========================");

        // 获取身份信息
        String principal = (String) authenticationToken.getPrincipal();
        // 在工厂工具类中获取service对象
        UserService userService = (UserService) ApplicationContextUtil.getBean("userService");
        User user = userService.findByUserName(principal);

        if(!ObjectUtils.isEmpty(user)){
            return  new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(), new SaltBySource(user.getSalt()),this.getName());
        }
        return null;
    }
}

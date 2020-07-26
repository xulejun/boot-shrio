package com.xlj.service;

import com.xlj.pojo.Permission;
import com.xlj.pojo.User;

import java.util.List;


public interface UserService {

    void register(User user);

    // 根据用户名查找该用户所有信息
    User findByUserName(String username);

    // 根据用户名查找其所拥有的所有角色
    User findRoleByUserName(String username);

    // 根绝角色id查询该所拥有的权限集合
    List<Permission> findPermissionByRoleId(String id);

}

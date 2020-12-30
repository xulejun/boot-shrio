package com.xlj.service.impl;

import com.xlj.pojo.Permission;
import com.xlj.pojo.User;
import com.xlj.dao.UserDao;
import com.xlj.util.*;
import com.xlj.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author XuLeJun
 * @Date 2020/7/17 18:17
 */

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void register(User user) {
        // 1.生成随机盐
        String salt = SaltUtil.getSalt(8);
        // 2.将随机盐保存到数据中
        user.setSalt(salt);
        // 3.明文密码进行md5 +salt +hash散列
        Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024); // 1024为散列次数
        user.setPassword(md5Hash.toHex());
        userDao.save(user);
    }

    @Override
    public User findByUserName(String username) {
        return userDao.findByUserName(username);
    }

    @Override
    public User findRoleByUserName(String username) {
        return userDao.findRoleByUserName(username);
    }

    @Override
    public List<Permission> findPermissionByRoleId(String id) {
        return userDao.findPermissionByRoleId(id);
    }
}

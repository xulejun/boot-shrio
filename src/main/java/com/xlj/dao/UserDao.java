package com.xlj.dao;

import com.xlj.bean.Role;
import com.xlj.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {
    void save(User user);

    User findByUserName(String username);

    // 根据用户名查询所有角色
    List<Role> findRoleByUserName(String username);
}

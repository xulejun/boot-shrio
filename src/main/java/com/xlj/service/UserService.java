package com.xlj.service;

import com.xlj.bean.Role;
import com.xlj.bean.User;

import java.util.List;

public interface UserService {
    void register(User user);

    User findByUserName(String username);

    List<Role> findRoleByUserName(String username);

}

package com.xlj.controller;

import com.xlj.bean.User;
import com.xlj.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author XuLeJun
 * @Date 2020/7/17 15:24
 */


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    // 用户注册认证
    @RequestMapping("/register")
    public String register(User user){

        try {
            userService.register(user);
            return "redirect:/login.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/register.jsp";
        }

    }

    // 用来处理身份认证
    @RequestMapping("/login")
    public String login(String username,String password){
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username,password));
            return "redirect:/index.jsp";
        } catch (UnknownAccountException e) {
            System.out.println("用户名错误");
        }catch (IncorrectCredentialsException e){
            System.out.println("密码错误");
        }
        return "redirect:/login.jsp";
    }
}

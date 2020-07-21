package com.xlj.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @Author XuLeJun
 * @Date 2020/7/19 17:27
 *
 * 以代码的形式对权限进行控制
 */


@Controller
@RequestMapping("order")
public class OrderController {

    @RequestMapping("save")
//    @RequiresRoles("admin")     // 单个角色-以注解的形式对权限进行控制
//    @RequiresRoles(value={"admin","user")     // 多个角色-以注解的形式对权限进行控制
//    @RequiresPermissions("user:update:01")      // 角色对资源的控制
    public String save(){
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("admin")) {
            System.out.println("保存订单");
        }else {
            System.out.println("无权访问");
        }
        return "redirect:/index.jsp";

    }

}

package com.xlj.controller;

import com.xlj.pojo.User;
import com.xlj.service.UserService;
import com.xlj.util.ImageUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * @Author XuLeJun
 * @Date 2020/7/17 15:24
 */


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    // 验证方法
    @RequestMapping("/getImage")
    public void getImage(HttpSession httpSession, HttpServletResponse response) throws IOException {
        // 获取随机验证码图片
        Map<String, Object> map = ImageUtil.generateCodeAndPic();
        // 验证码放入session中
        httpSession.setAttribute("code", map.get("code"));
        // 验证码存入图片
        response.setContentType("image");
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write((RenderedImage) map.get("codePic"), "jpeg", out);
    }

    // 用户注册认证
    @RequestMapping("/register")
    public String register(User user) {

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
    public String login(String username, String password, String code, HttpSession session) {
        // 比较验证码
        StringBuffer codes = (StringBuffer) session.getAttribute("code");
        try {
            if (code.equalsIgnoreCase(String.valueOf(codes))) {
                Subject subject = SecurityUtils.getSubject();
                subject.login(new UsernamePasswordToken(username, password));
                return "redirect:/index.jsp";
            } else {
                throw new RuntimeException("验证码错误！");
            }
        } catch (UnknownAccountException e) {
            System.out.println("用户名错误");
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误");
        } catch (RuntimeException e) {
            e.getStackTrace();
            System.out.println(e.getMessage());
        }

        return "redirect:/login.jsp";
    }
}

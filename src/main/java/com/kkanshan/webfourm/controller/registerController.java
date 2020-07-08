package com.kkanshan.webfourm.controller;

import com.kkanshan.webfourm.entity.User;
import com.kkanshan.webfourm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class registerController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @PostMapping("/registerCheck")
    public String registerCheck(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String firstPassword = request.getParameter("firstPassword");
        System.out.println("帐号密码"+username+firstPassword);
        //随机生成一个token用来当cookies的value
        String token = UUID.randomUUID().toString();
        User user = new User();
        user.setName(username);
        user.setPassword(firstPassword);
        user.setToken(token);
        userMapper.insert(user);
        //如果用户注册成功，则把用户信息写进session，直接跳转到主页
        if (userMapper.select(user) != null) {
            response.addCookie(new Cookie("token", token));
            return "redirect:/index";
        } else {
            //注册失败，处理方法先省略
            return "register";
        }
    }
}

package com.kkanshan.webfourm.controller;

import com.kkanshan.webfourm.entity.User;
import com.kkanshan.webfourm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class loginController {
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/loginCheck")
    public String loginCheck(HttpServletRequest request, HttpServletResponse response, Model model) {
        //通过request获取输入的用户名和密码在数据库中查找相关用户，如果存在就登陆成功
        User user = new User();
        String name = request.getParameter("username");
        String password = request.getParameter("password");
        user.setName(name);
        user.setPassword(password);
        User newUser = userMapper.select(user);
        if (newUser != null) {
            String token = newUser.getToken();
            response.addCookie(new Cookie("token", token));
            return "redirect:/index";
        } else {
            model.addAttribute("msg","用户名或密码错误");
            return "login";
        }
    }

    //退出登陆
    @GetMapping("/loginOut")
    public String loginOut(HttpServletRequest request,
                         HttpServletResponse response){
        request.getSession().removeAttribute("user");
        Cookie cookie=new Cookie("token",null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/index";
    }
}

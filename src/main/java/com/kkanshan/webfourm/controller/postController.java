package com.kkanshan.webfourm.controller;

import com.kkanshan.webfourm.entity.Question;
import com.kkanshan.webfourm.entity.User;
import com.kkanshan.webfourm.mapper.QuestionMapper;
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

//未完成tag的获取
public class postController {


    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    @GetMapping("/post")
    public String post(){
        return ("post");
    }

    @PostMapping("/postCheck")
    public String postCheck(HttpServletRequest request,
                            Model model){

        String title = request.getParameter("title");
        String description=request.getParameter("description");



        //获取当前登陆用户的信息
        User user=null;
        Cookie[] cookies=request.getCookies();
        for (Cookie cookie:cookies){
            if(cookie.getName().equals("token")){
                String token=cookie.getValue();
                user=userMapper.findBytoken(token);
            }
        }


        //随机生成id号
        int id = user.getId()*100000+(int)(Math.random()*10000);


        Question question=new Question();
        question.setTitle(title);
        question.setCreateId(user.getId());
        question.setDescription(description);
        question.setId(id);
        question.setComment_count(0);
        question.setLike_count(0);
        question.setView_count(0);
        question.setTag("未完成");


        questionMapper.createQuestion(question);

        return "index";
    }

}

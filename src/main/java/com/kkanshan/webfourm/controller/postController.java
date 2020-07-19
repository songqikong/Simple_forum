package com.kkanshan.webfourm.controller;

import com.kkanshan.webfourm.entity.Question;
import com.kkanshan.webfourm.entity.User;
import com.kkanshan.webfourm.entity.postPage;
import com.kkanshan.webfourm.mapper.QuestionMapper;
import com.kkanshan.webfourm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Date;

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
                            HttpServletResponse response,
                            Model model){

        String title = request.getParameter("title");
        String description=request.getParameter("description");
        String part = request.getParameter("options");


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
        java.util.Date data = new java.util.Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm");

        Question question=new Question();
        question.setTitle(title);
        question.setCreateId(user.getId());
        question.setDescription(description);
        question.setId(id);
        question.setComment_count(0);
        question.setLike_count(0);
        question.setView_count(0);
        question.setTag(Integer.valueOf(part));
        question.setCreateTime(ft.format(data));


        questionMapper.createQuestion(question);

        return "index";
    }

    @ResponseBody
    @GetMapping("/getPost/{tag}/{page}")
    public postPage getPost(@PathVariable int page, @PathVariable int tag){
        if(tag == 0){
            List<Question> list = questionMapper.listByTag(tag,(page-1)*15,15);
            return new postPage(list,
                    questionMapper.count()/15+(questionMapper.countTag(tag)%15!=0 ? 1:0));
        }else if(tag == 1){
            List<Question> list = questionMapper.listByTag(tag,(page-1)*15,15);
            return new postPage(list,
                    questionMapper.count()/15+(questionMapper.countTag(tag)%15!=0 ? 1:0));
        }else if (tag == 2){
            List<Question> list = questionMapper.listByTag(tag,(page-1)*15,15);
            return new postPage(list,
                    questionMapper.count()/15+(questionMapper.countTag(tag)%15!=0 ? 1:0));
        }

        List<Question> list = questionMapper.list((page-1)*15,15);
        return new postPage(list,
                questionMapper.count()/15+(questionMapper.count()%15!=0 ? 1:0));
    }


}

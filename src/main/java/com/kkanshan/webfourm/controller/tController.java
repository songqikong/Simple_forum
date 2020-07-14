package com.kkanshan.webfourm.controller;

import com.kkanshan.webfourm.entity.Comment;
import com.kkanshan.webfourm.entity.Question;
import com.kkanshan.webfourm.entity.User;
import com.kkanshan.webfourm.mapper.CommentMapper;
import com.kkanshan.webfourm.mapper.QuestionMapper;
import com.kkanshan.webfourm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

@Controller
public class tController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    CommentMapper commentMapper;

    @GetMapping("/t")
    public String t(){
        return "t";
    }

    //用于从getQuestion传送信息到tCheck
    Question qt;

    @GetMapping("/t/{id}")
    public String getQuestion(@PathVariable(name = "id")int id,
                              Model model, HttpServletRequest request){
        //Cookie[] cookies = request.getCookies();
        //if (cookies == null) {
        //    return "login";
        //}

        Question question = questionMapper.getbyId(id);
        qt=question;
        question.setView_count(question.getView_count()+1);
        questionMapper.updateView(question.getId());
        User user = userMapper.findById(question.getCreateId());
        model.addAttribute(question);
        model.addAttribute(user);

        return "t";
    }

    @PostMapping("/tCheck")
    public String tCheck(HttpServletRequest request,
                         HttpServletResponse response,
                         Model model) {

        //查找cookies，获取当前用户的信息
        Cookie[] cookies=request.getCookies();
        if(cookies==null){
            return "login";
        }
        User user=null;
        for (Cookie cookie:cookies){
            if(cookie.getName().equals("token")){
                String token=cookie.getValue();
                user=userMapper.findBytoken(token);
                if(user!=null){
                    request.getSession().setAttribute("user",user);
                }
                break;
            }
        }

        //获取用户输入的评论
        String tText = request.getParameter("tText");

        //获取字符串类型的时间
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm");
        java.util.Date data = new java.util.Date();

        Comment c = new Comment();

        c.setParent_id(qt.getCreateId());
        c.setCommentCount(0);
        c.setCommenter(user.getId());
        c.setContent(tText);
        c.setLike_count(0);
        c.setType(1);//二级回复，暂未实现，统一设置1
        c.setCreateTime(ft.format(data));
        commentMapper.insert(c);

        //回到当前页面
        //System.out.println(qt.getId());
        return "redirect:/t/"+qt.getId();
    }




}

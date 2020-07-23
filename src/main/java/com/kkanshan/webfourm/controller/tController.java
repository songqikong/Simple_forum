package com.kkanshan.webfourm.controller;

import com.kkanshan.webfourm.entity.Comment;
import com.kkanshan.webfourm.entity.Question;
import com.kkanshan.webfourm.entity.User;
import com.kkanshan.webfourm.mapper.CommentMapper;
import com.kkanshan.webfourm.mapper.QuestionMapper;
import com.kkanshan.webfourm.mapper.UserMapper;
import org.apache.ibatis.annotations.Param;
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
import java.util.ArrayList;
import java.util.List;

@Controller
public class tController {

    private int tid;

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
        tid = id;
        int curId = -1;

        //帖子信息区
        Question question = questionMapper.getbyId(id);
        qt=question;
        question.setView_count(question.getView_count()+1);
        questionMapper.updateView(question.getId());
        User user = userMapper.findById(question.getCreateId());
        model.addAttribute(question);
        //查找cookies，获取当前用户的信息
        Cookie[] cookies=request.getCookies();
        /*if(cookies==null){
            model.addAttribute(curId);
        }*/
        User curuser=null;
        for (Cookie cookie:cookies){
            if(cookie.getName().equals("token")){
                String token=cookie.getValue();
                curuser=userMapper.findBytoken(token);
                break;
            }
        }
        assert curuser != null;
        model.addAttribute(curuser);

        //回复区
        List<Comment> comment = commentMapper.getByParentId(question.getId());
//        System.out.println(question.getId());
        for(int i=0;i<comment.size();i++){
//            System.out.println(comment.get(i).getContent());
        }
        model.addAttribute(comment);

        //获取的comment的createId，还需要通过user表找出createId的name
        List<User> u2=userMapper.listFindByCommentId(question.getId());
        for(int i=0;i<u2.size();i++){
//            System.out.println(u2.get(i).getName());
        }
        model.addAttribute(u2);

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

        c.setParent_id(qt.getId());
        c.setCommentCount(0);
        c.setCommenter(user.getId());
        c.setContent(tText);
        c.setLike_count(0);
        c.setType(1);//二级回复，暂未实现，统一设置1
        c.setCreateTime(ft.format(data));
        commentMapper.insert(c);
        questionMapper.updateComment(tid);

        //回到当前页面
        //System.out.println(qt.getId());
        return "redirect:/t/"+qt.getId();
    }




}

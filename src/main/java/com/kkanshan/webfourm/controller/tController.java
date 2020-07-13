package com.kkanshan.webfourm.controller;

import com.kkanshan.webfourm.entity.Question;
import com.kkanshan.webfourm.entity.User;
import com.kkanshan.webfourm.mapper.QuestionMapper;
import com.kkanshan.webfourm.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class tController {

    @Autowired
    UserMapper userMapper;

    @Autowired
    QuestionMapper questionMapper;

    @GetMapping("/t")
    public String t(){
        return "t";
    }

    @GetMapping("/t/{id}")
    public String getQuestion(@PathVariable(name = "id")int id, Model model, HttpServletRequest request){
        //Cookie[] cookies = request.getCookies();
        //if (cookies == null) {
        //    return "login";
        //}

        Question question = questionMapper.getbyId(id);
        question.setView_count(question.getView_count()+1);
        questionMapper.updateView(question.getId());
        User user = userMapper.findById(question.getCreateId());
        model.addAttribute(question);
        model.addAttribute(user);

        return "t";
    }
}

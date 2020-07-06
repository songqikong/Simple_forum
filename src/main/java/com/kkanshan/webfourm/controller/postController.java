package com.kkanshan.webfourm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class postController {

    @GetMapping("/post")
    public String post(){
        return ("post");
    }
}

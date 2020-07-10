package com.kkanshan.webfourm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class notiController {

    @GetMapping("/noti")
    public String noti(){
        return "noti";
    }
}

package com.kkanshan.webfourm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class tController {

    @GetMapping("/t")
    public String t(){
        return "t";
    }
}

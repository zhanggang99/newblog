package com.zhanggang.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {
    @GetMapping("blog")
    public String blog(){
        return "blog";
    }
}

package com.zhanggang.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TagsController {

    @GetMapping("/tags")
    public String tags(){

        return "/tags";
    }

}

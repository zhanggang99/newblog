package com.zhanggang.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TagsController {

    @GetMapping("/tags/{id}")
    public String tags(@PathVariable Long id){

        return "/tags";
    }

}

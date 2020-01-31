package com.zhanggang.blog.web;

import com.zhanggang.blog.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class indexController {
    @GetMapping("blog/{id}/{name}")
    public String blog(@PathVariable Integer id, @PathVariable String name){
//        int a = 9/0;
//        String blog = null;
//        if (blog==null){
//            throw new NotFoundException("博客找不到");
//        }
        System.out.println("------index-------");
        return "blog";
    }
}

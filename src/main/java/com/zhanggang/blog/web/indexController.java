package com.zhanggang.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {
    @GetMapping("/blog")
    //public String blog(@PathVariable Integer id, @PathVariable String name){
    public String blog(){
//        int a = 9/0;
//        String blog = null;
//        if (blog==null){
//            throw new NotFoundException("博客找不到");
//        }
        System.out.println("------index-------");
        return "blog";
    }
}

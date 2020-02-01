package com.zhanggang.blog.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @GetMapping("/blogs")
    public String blogs(){
        return "admin/blogs";
    }
//    @GetMapping("/admin/blogsinput")
//    public String bloginput(){
//        return "admin/blogsinput";
//    }

//    @GetMapping("/admin/login")
//    public String login(){
//        return "/admin/login";
//    }

}

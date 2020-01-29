package com.zhanggang.blog.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {
    @GetMapping("/admin/blog")
    public String blog(){
        return "blogs";
    }
    @GetMapping("/admin/blogsinput")
    public String bloginput(){
        return "admin/blogsinput";
    }
}

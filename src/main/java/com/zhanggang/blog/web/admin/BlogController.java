package com.zhanggang.blog.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogController {
    @GetMapping("/admin/index")
    public String blog(){
        return "admin/index";
    }
    @GetMapping("/admin/blogsinput")
    public String bloginput(){
        return "admin/blogsinput";
    }

    @GetMapping("/admin/login")
    public String login(){
        return "/admin/login";
    }

}

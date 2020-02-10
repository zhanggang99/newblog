package com.zhanggang.blog.web;

import com.zhanggang.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArchivesController {

    @Autowired
    BlogService blogService;
    @GetMapping("/archives")
    public String archives(Model model){
        model.addAttribute("mapBlogs",blogService.mapBlog());
        model.addAttribute("countBlog",blogService.countBlog());
        return "archives";
    }
}

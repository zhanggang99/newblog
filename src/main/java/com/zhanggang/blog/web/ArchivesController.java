package com.zhanggang.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArchivesController {

    @GetMapping("/archives")
    public String archives(Model model){
        model.addAttribute("page","");
        return "archives";
    }
}

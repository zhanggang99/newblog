package com.zhanggang.blog.web.admin;

import com.zhanggang.blog.po.Blog;
import com.zhanggang.blog.service.BlogService;
import com.zhanggang.blog.service.TypeService;
import com.zhanggang.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;

    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 3,sort = {"id"},direction = Sort.Direction.DESC)Pageable pageable, BlogQuery blog, Model model){
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        model.addAttribute("types",typeService.listType());
        return "admin/blogs";
    }
    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 3,sort = {"id"},direction = Sort.Direction.DESC)Pageable pageable, BlogQuery blog, Model model){
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return "admin/blogs :: blogList";
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

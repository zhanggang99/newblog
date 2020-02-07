package com.zhanggang.blog.web;

import com.zhanggang.blog.dao.BlogRespository;
import com.zhanggang.blog.service.BlogService;
import com.zhanggang.blog.service.TagService;
import com.zhanggang.blog.service.TypeService;
import com.zhanggang.blog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexController {
    @Autowired
    BlogService blogService;
    @Autowired
    TypeService typeService;
    @Autowired
    TagService tagService;
    @GetMapping("/")
    //public String blog(@PathVariable Integer id, @PathVariable String name){
    public String blog(@PageableDefault(size = 3,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("page",blogService.listBlog(pageable));
        model.addAttribute("types",typeService.listTypeTop(6));
        model.addAttribute("tags",tagService.listTagTop(10));
        model.addAttribute("recommendBlogs", blogService.listRecommendBlogTop(8));
        System.out.println("------index-------");
        return "/index";
    }
}

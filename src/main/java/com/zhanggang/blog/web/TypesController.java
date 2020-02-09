package com.zhanggang.blog.web;

import com.zhanggang.blog.po.Type;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class TypesController {

    @Autowired
    TypeService typeService;
    @Autowired
    BlogService blogService;
//
//    @GetMapping("/types")
//    public String types(Model model){
//        model.addAttribute("types",typeService.listTypeTop(10000));
//        return "types";
//    }

    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable, @PathVariable Long id, Model model){
        List<Type> types = typeService.listTypeTop(10000);
        if (id == -1){
            id=types.get(0).getId();
        }
        BlogQuery query = new BlogQuery();
        query.setTypeId(id);
        model.addAttribute("types",types);
        model.addAttribute("page",blogService.listBlog(pageable,query));
        model.addAttribute("activeTypeId",id);
        return "types";
    }
}

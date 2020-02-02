package com.zhanggang.blog.web.admin;

import com.zhanggang.blog.po.Type;
import com.zhanggang.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    TypeService typeService;

    @GetMapping("/types")
    public String types(@PageableDefault(size = 3,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("page",typeService.listType(pageable));
        return "/admin/types";
    }

    @GetMapping("/types/input")
    public String input(Model model)
    {
        model.addAttribute("type",new Type());
        return "/admin/typeinput";
    }

    @PostMapping("types")
    public String types(@Valid Type type, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        Type tt = typeService.getTypeByName(type.getName());
        if (tt != null){
            bindingResult.rejectValue("name","nameError","不能添加重复的分类！");
        }
        if (bindingResult.hasErrors()){
            return "admin/typeinput";
        }
        Type t = typeService.saveType(type);
        if (t == null){
            redirectAttributes.addFlashAttribute("message","操作失败！");
        }else{
            redirectAttributes.addFlashAttribute("message","操作成功！");
        }
        return "redirect:/admin/types";
    }
}

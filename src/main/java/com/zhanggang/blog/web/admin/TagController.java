package com.zhanggang.blog.web.admin;

import com.zhanggang.blog.po.Tag;
import com.zhanggang.blog.po.Type;
import com.zhanggang.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.crypto.interfaces.PBEKey;
import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    TagService tagService;

    @GetMapping("/tags")
    @Transactional
    public String tags(@PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model){
        Page<Tag> page = tagService.listTag(pageable);
        model.addAttribute("page",page);
        return "/admin/tags";
    }

    @GetMapping("/tags/input")
    @Transactional
    public String input(Model model){
        model.addAttribute("tag",new Tag());
        return "admin/taginput";
    }

    @GetMapping("/tags/{id}/input")
    public String editinput(@PathVariable Long id, Model model){
        model.addAttribute("tag",tagService.getTag(id));
        return "/admin/taginput";
    }
    @PostMapping("/tags")
    public String types(@Valid Tag tag, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        Tag tt = tagService.getTagByName(tag.getName());
        if (tt != null){
            bindingResult.rejectValue("name","nameError","不能添加重复的标签！");
        }
        if (bindingResult.hasErrors()){
            return "admin/taginput";
        }
        Tag t = tagService.saveTag(tag);
        if (t == null){
            redirectAttributes.addFlashAttribute("message","新增失败！");
        }else{
            redirectAttributes.addFlashAttribute("message","新增成功！");
        }
        return "redirect:/admin/tags";
    }
    @PostMapping("/tags/{id}")
    public String editpost(@Valid Tag tag, BindingResult bindingResult,@PathVariable Long id, RedirectAttributes redirectAttributes){
        Tag tt = tagService.getTagByName(tag.getName());
        if (tt != null){
            bindingResult.rejectValue("name","nameError","不能添加重复的标签！");
        }
        if (bindingResult.hasErrors()){
            return "admin/taginput";
        }
        Tag t = tagService.updateTag(id,tag);
        if (t == null){
            redirectAttributes.addFlashAttribute("message","更新失败！");
        }else{
            redirectAttributes.addFlashAttribute("message","更新成功！");
        }
        return "redirect:/admin/tags";
    }

    @GetMapping("/tags/{id}/delete")
    @Transactional
    public String delete(@PathVariable Long id,RedirectAttributes redirectAttributes){
        tagService.deleteTag(id);
        redirectAttributes.addFlashAttribute("message","删除成功");
        return "redirect:/admin/tags";
    }

}

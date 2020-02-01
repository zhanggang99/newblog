package com.zhanggang.blog.web.admin;

import com.zhanggang.blog.po.User;
import com.zhanggang.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    UserService userService;
    @GetMapping
    public String loginPage(){
        return "/admin/login";
    }
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, HttpSession session, RedirectAttributes attributes){
        User user = userService.checkUser(username,password);
        if (user != null){
            user.setPassword(null);
            session.setAttribute("user",user);
            return "/admin/index";
        }else{
//            return "/admin/login";  这种方式不行，会导致后续路径处理有问题。
             attributes.addFlashAttribute("message","用户名或密码错误！");
            return "redirect:/admin";//重定向方式
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
         session.removeAttribute("user");
         return "redirect:/admin";
    }
}

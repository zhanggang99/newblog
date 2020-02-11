package com.zhanggang.blog.web;

import com.zhanggang.blog.AdminProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    //通过 @value注释可取得配置中的值。
    @Value("${adminname}")
    private String adminname;

    @Autowired
    AdminProperties admin;

    @GetMapping({"/hello","/hi"})
    public String say(){
        //return adminname;
        return admin.getName()+":"+admin.getAge();
    }

    //对于使用 controller时，需要配合使用 html 引擎模板，如 thymeleaf等。返回 index(.html)来显示页面。
    //@RestControll = @Controll +@ResponseStatus
}

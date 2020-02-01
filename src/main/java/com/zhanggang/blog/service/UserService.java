package com.zhanggang.blog.service;

import com.zhanggang.blog.po.User;

public interface UserService {
    User checkUser(String username,String password);
}

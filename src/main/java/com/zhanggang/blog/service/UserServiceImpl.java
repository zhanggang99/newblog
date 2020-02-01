package com.zhanggang.blog.service;

import com.zhanggang.blog.dao.UserRespository;
import com.zhanggang.blog.po.User;
import com.zhanggang.blog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRespository userRespository;
    @Override
    public User checkUser(String username, String password) {
        User user = userRespository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}

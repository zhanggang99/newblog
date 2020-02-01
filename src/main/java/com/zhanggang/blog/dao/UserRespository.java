package com.zhanggang.blog.dao;

import com.zhanggang.blog.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<User,Long> {
    User findByUsernameAndPassword(String username,String password);
}

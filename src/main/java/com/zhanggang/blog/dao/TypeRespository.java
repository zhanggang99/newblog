package com.zhanggang.blog.dao;

import com.zhanggang.blog.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRespository extends JpaRepository<Type,Long> {
    Type findByName(String name);
}

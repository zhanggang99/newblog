package com.zhanggang.blog.dao;

import com.zhanggang.blog.po.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRespository extends JpaRepository<Tag,Long> {
    Tag getTagByName(String name);
}

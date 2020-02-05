package com.zhanggang.blog.dao;

import com.zhanggang.blog.po.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BlogRespository extends JpaRepository<Blog, Long> , JpaSpecificationExecutor<Blog> {
}

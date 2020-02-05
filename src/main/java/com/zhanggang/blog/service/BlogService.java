package com.zhanggang.blog.service;

import com.zhanggang.blog.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService {
    Blog getBlog(Long id);
    Blog saveBlog(Blog blog);
    Page<Blog> listBlog(Pageable pageable,Blog blog);
    Blog updateBlog(Long id,Blog blog);
    void deleteBlog(Long id);
}
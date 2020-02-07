package com.zhanggang.blog.service;

import com.zhanggang.blog.po.Blog;
import com.zhanggang.blog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {
    Blog getBlog(Long id);
    Blog saveBlog(Blog blog);
    Page<Blog> listBlog(Pageable pageable);
    Page<Blog> listBlog(String query,Pageable pageable);
    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);
    Blog updateBlog(Long id,Blog blog);
    void deleteBlog(Long id);
    List<Blog> listRecommendBlogTop(Integer size);
}

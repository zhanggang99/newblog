package com.zhanggang.blog.dao;

import com.zhanggang.blog.po.Blog;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRespository extends JpaRepository<Blog, Long> , JpaSpecificationExecutor<Blog> {
    @Query("select b from Blog b where b.recommend = true")
    List<Blog> findTop(Pageable pageable);
}

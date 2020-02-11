package com.zhanggang.blog.dao;

import com.zhanggang.blog.po.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRespository extends JpaRepository<Student,Integer> {
    List<Student> findByAge(Integer age);
}

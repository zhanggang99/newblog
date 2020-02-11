package com.zhanggang.blog.service;

import com.zhanggang.blog.dao.StudentRespository;
import com.zhanggang.blog.po.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {
    @Autowired
    private StudentRespository studentRespository;

    @Transactional
    public void insertTowStudent() throws Exception{
        Student a = new Student();
        a.setName("zg1");
        a.setAge(22);
        studentRespository.save(a);

        Integer i = 1/0;
        Student b = new Student();
        b.setAge(33);
        b.setName("zg2");
        studentRespository.save(b);

    }
}

package com.zhanggang.blog.service;

import com.zhanggang.blog.dao.TypeRespository;
import com.zhanggang.blog.po.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    TypeRespository typeRespository;

    @Override
    @Transactional
    public Type saveType(Type type) {
        return (Type) typeRespository.save(type);
    }

    @Override
    @Transactional
    public Type getType(Long id) {
        return (Type) typeRespository.getOne(id);
    }

    @Override
    @Transactional
    public Page<Type> listType(Pageable pageable) {
        return typeRespository.findAll(pageable);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeRespository.findByName(name);
    }
}

package com.zhanggang.blog.service;

import com.zhanggang.blog.NotFoundException;
import com.zhanggang.blog.dao.TypeRespository;
import com.zhanggang.blog.po.Type;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Override
    public void deleteType(Long id) {
        typeRespository.deleteById(id);
    }

    @Override
    public List<Type> listTypeTop(Integer size) {

//        Sort sort = new Sort(
//                new Sort.Order(Sort.Direction.ASC, Constants.PARAM_CREATED_DATE)
//        );
//        PageRequest pageable = new PageRequest(page, 20, sort);
//        return new AsyncResult<>(threadRepository.findAll(pageable));
        //Sort sort = new Sort(Sort.Direction.DESC,"blogs.size");

//        Sort sort = new Sort(Sort.Direction.DESC,"blogs.size");
//        Pageable pageable = new PageRequest(0,size,sort);
//
//        return typeRespository.findTop(pageable);

        Sort.Order order=new Sort.Order(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = PageRequest.of(0,size,Sort.by(order));
        return typeRespository.findTop(pageable);
    }

    @Override
    public List<Type> listType() {
        return typeRespository.findAll();
    }

    @Override
    @Transactional
    public Type updateType(Long id, Type type) {
        Type t = typeRespository.getOne(id);
        if (t == null){
            throw  new NotFoundException("不存在此类型");
        }
        BeanUtils.copyProperties(type,t);
        return typeRespository.save(t);
    }
}

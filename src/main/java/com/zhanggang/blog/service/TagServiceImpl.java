package com.zhanggang.blog.service;

import com.zhanggang.blog.NotFoundException;
import com.zhanggang.blog.dao.TagRespository;
import com.zhanggang.blog.po.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class TagServiceImpl implements TagService {

    @Override
    public List<Tag> listTagTop(Integer size) {
//        List<String> list = new ArrayList<>();
//        list.add("blogs.size");
//        Sort.Order order = new Sort.Order(Sort.Direction.DESC,"blogs.size",null);
//        List<Sort.Order> orderList = new ArrayList<>();
//        orderList.add(order);
//        Sort sort = new Sort(Sort.Direction.DESC,orderList);
//        Pageable pageable= new PageRequest(0,size,sort);
//        List<String> list = new ArrayList<>();
//        list.add("blogs.size");
        //Sort sort = new Sort(Sort.Direction.DESC,"blogs.size");

        //Sort sort = new Sort(Sort.Direction.DESC,list);
        //Pageable pageable = new PageRequest(0,6,sort);


//        List<Sort.Order> orderList = new ArrayList<Sort.Order>();
//        Sort.Order order = new Sort.Order(Sort.Direction.DESC,"blogs.size",null);
//        orderList.add(order);
//        List<String> list1 = Arrays.asList("blogs.size");// ["blogs.size"];
//        Sort sort = new Sort(Sort.Direction.DESC, list1);
//        Pageable pageable = new PageRequest(0,6,sort);


//        List<Order> orders=new ArrayList<Sort.Order>();
//        orders.add(new Order(Direction.DESC, "_state"));
//        orders.add(new Order(Direction.DESC, "createtime"));
//        //Sort sort = new Sort(Sort.Direction.DESC,"createtime");
//        Page<SpecialguardInfo> page1=findAllByParam(paramMap, page, rows, SpecialguardInfo.class, new Sort(orders));//注意参数要修改成这样
//        return page1;
//        List<Sort.Order> orders = new ArrayList<Sort.Order>();
//        orders.add(new Sort.Order(Sort.Direction.DESC,"blogs.size"));
//        Sort sort = new Sort(Sort.Direction.DESC,"blogs.size");
//        Pageable pageable = new PageRequest(0,6,sort);

        Sort.Order order=new Sort.Order(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = PageRequest.of(0,size,Sort.by(order));

        return tagRespository.findTop(pageable);
    }

    @Override
    public List<Tag> listTag(String ids) {
        return tagRespository.findAllById(convertToList(ids));
    }

    @Override
    public List<Tag> listTag() {
        return tagRespository.findAll();
    }

    @Autowired
    TagRespository tagRespository;
    @Override
    public Tag getTag(Long id) {
        return tagRespository.getOne(id);
    }

    @Override
    public Tag saveTag(Tag tag) {
        return tagRespository.save(tag);
    }

    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRespository.findAll(pageable);
    }

    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag tag1 = tagRespository.getOne(id);
        if (tag1 == null){
            throw new NotFoundException("不存在标签!");
        }
        BeanUtils.copyProperties(tag,tag1);
        return tagRespository.save(tag1);
    }

    @Override
    public void deleteTag(Long id) {
        tagRespository.deleteById(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagRespository.getTagByName(name);
    }

    private List<Long> convertToList(String ids){
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null){
            String[] idList = ids.split(",");
            for (int i = 0;i<idList.length;i++){
                list.add( new Long(idList[i]));
            }
        }
        return list;
    }
}

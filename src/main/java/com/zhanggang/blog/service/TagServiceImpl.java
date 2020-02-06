package com.zhanggang.blog.service;

import com.zhanggang.blog.NotFoundException;
import com.zhanggang.blog.dao.TagRespository;
import com.zhanggang.blog.po.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {

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
            throw  new NotFoundException("不存在标签!");
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

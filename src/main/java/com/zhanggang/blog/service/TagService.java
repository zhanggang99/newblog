package com.zhanggang.blog.service;

import com.zhanggang.blog.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TagService {
    Tag getTag(Long id);
    Tag saveTag(Tag tag);
    Page<Tag> listTag(Pageable pageable);
    Tag updateTag(Long id,Tag tag);
    void deleteTag(Long id);

    Tag getTagByName(String name);
}

package com.zhanggang.blog.service;

import com.zhanggang.blog.dao.CommentRespository;
import com.zhanggang.blog.po.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRespository commentRespository;
    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
//        Sort sort = new Sort(Sort.Direction.DESC,"createTime");
        Sort.Order order = new Sort.Order(Sort.Direction.DESC,"createTime");

        return commentRespository.findByBlogId(blogId, Sort.by(order));
    }

    @Override
    public Comment saveComment(Comment comment) {
        Long parentCommendId = comment.getParentComment().getId();
        if(parentCommendId != -1){
            comment.setParentComment(commentRespository.getOne(parentCommendId));
        }else{
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentRespository.save(comment);
    }
}

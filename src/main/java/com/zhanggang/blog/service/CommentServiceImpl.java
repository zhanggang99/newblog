package com.zhanggang.blog.service;

import com.zhanggang.blog.dao.CommentRespository;
import com.zhanggang.blog.po.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Relation;
import java.util.ArrayList;
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
        Sort.Order order = new Sort.Order(Sort.Direction.ASC,"createTime");
        List<Comment> comments = commentRespository.findByBlogIdAndParentCommentNull(blogId, Sort.by(order));
        return eachComment(comments);
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

    //存放迭代找出的所有子代的集合
    private List<Comment> tempReplys = new ArrayList<>();

    private List<Comment> eachComment(List<Comment> comments){
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : comments){
            Comment c = new Comment();
            BeanUtils.copyProperties(comment,c);
            commentsView.add(c);
        }
        //合并评论的各层子代到第一级子代集合中
        combineChile(commentsView);
        return commentsView;
    }
    private void combineChile(List<Comment> comments){
        for (Comment comment : comments){
            List<Comment> replys1 = comment.getReplyComments();
            for (Comment reply1 : replys1){
                //循环迭代，找出子代，存放在tempReplys中
                recursively(reply1);
            }
            //修改顶级节点的reply集合为迭代处理后的集合
            comment.setReplyComments(tempReplys);
            //清除临时存储区
            tempReplys = new ArrayList<>();
        }
    }
    private void recursively(Comment comment){
        tempReplys.add(comment);
        if (comment.getReplyComments().size()>0){
            List<Comment> replys = comment.getReplyComments();
            for (Comment reply : replys){
                tempReplys.add(reply);
                if (reply.getReplyComments().size()>0){
                    recursively(reply);
                }
            }
        }
    }

}

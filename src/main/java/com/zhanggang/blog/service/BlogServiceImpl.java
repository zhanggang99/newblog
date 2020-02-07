package com.zhanggang.blog.service;

import com.zhanggang.blog.NotFoundException;
import com.zhanggang.blog.dao.BlogRespository;
import com.zhanggang.blog.po.Blog;
import com.zhanggang.blog.po.Type;
import com.zhanggang.blog.util.MyBeanUtils;
import com.zhanggang.blog.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogRespository blogRespository;
    @Override
    public Blog getBlog(Long id) {
        return blogRespository.getOne(id);
    }

    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        //Sort sort =  new Sort(Sort.Direction.DESC,"updateTime");
        //Pageable pageable = new PageRequest(0,size,sort);
        //Error:(40, 51) java: 不兼容的类型: java.lang.String无法转换为java.util.List<java.lang.String>
        //Error:(41, 29) java: PageRequest(int,int,org.springframework.data.domain.Sort) 在 org.springframework.data.domain.PageRequest 中是 protected 访问控制
        Sort.Order order = new Sort.Order(Sort.Direction.DESC,"updateTime");
        Pageable pageable = PageRequest.of(0,size,Sort.by(order));
        return blogRespository.findTop(pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRespository.findAll(pageable);
    }

    @Override
    public Blog saveBlog(Blog blog) {
        if (blog.getId() == null){
            blog.setCreateTime(new Date());
            blog.setViews(0);
        }
        blog.setUpdateTime(new Date());
        return blogRespository.save(blog);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
        return blogRespository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicateList = new ArrayList<>();
                if(!"".equals(blog.getTitle()) && blog.getTitle() != null){
                    predicateList.add(cb.like(root.<String>get("title"),"%"+blog.getTitle()+"%"));
                }
                //if (blog.getType().getId() != null){
                if (blog.getTypeId() != null){
                    predicateList.add(cb.equal(root.<Type>get("type").get("id"),blog.getTypeId()));
                }
                //if (blog.isRecommend()){
                if (blog.isRecommend()){
                    predicateList.add(cb.equal(root.<Boolean>get("recommand"),blog.isRecommend()));
                }
                cq.where(predicateList.toArray(new Predicate[predicateList.size()]));
                return null;
            }
        },pageable);
    }

    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog blog1 = blogRespository.getOne(id);
        if (blog1 == null){
            throw new NotFoundException("没有找到对应的博客!");
        }
        BeanUtils.copyProperties(blog,blog1, MyBeanUtils.getNullPropertyNames(blog));//过滤掉属性为空的 blog 属性。
        blog1.setUpdateTime(new Date());
        return blogRespository.save(blog1);
    }

    @Override
    public void deleteBlog(Long id) {
        blogRespository.deleteById(id);
    }
}

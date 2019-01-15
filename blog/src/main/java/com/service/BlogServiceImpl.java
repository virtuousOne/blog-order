package com.service;

import com.NotFoundException;
import com.dao.BlogRepository;
import com.po.Blog;
import com.po.Type;
import com.util.MarkdownUtils;
import com.util.MyBeanUtils;
import com.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * @Description: 实现博客service
 * @Author: 吴宸煊
 * @Date: Created in  2017/12/25
 */
@Service
public class BlogServiceImpl implements  BlogService {



    @Autowired
    private BlogRepository blogRepository;

    //获得归档的博客数量
    @Override
    public Long countBlog() {
        return blogRepository.count();
    }

    //根据id查找blog
    public Blog getBlog(Long id) {
        return blogRepository.findOne(id);
    }

    //分页动态查询查询
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates=new ArrayList<>();
                if(!"".equals(blog.getTitle()) && blog.getTitle()!=null ){
                    predicates.add(cb.like(root.<String>get("title"),"%"+blog.getTitle()));//标题查询
                }
                if(blog.getTypeId()!=null){
                    predicates.add(cb.equal(root.<Type>get("type").get("id"),blog.getTypeId()));//分类查询的条件
                }
                if(blog.isRecommend()){
                    predicates.add(cb.equal(root.<Boolean>get("recommend"),blog.isRecommend()));//是否推荐查询
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }
    //前端查找博客
    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

    //前端查找，search
    @Override
    public Page<Blog> listBlog(Pageable pageable, String query) {

        return blogRepository.findByQuery(query,pageable);
    }

    //获取博客并进行文本转换
    @Override
    public Blog getAndConvert(Long id) {
        Blog blog=blogRepository.findOne(id);
        if(blog==null){
            throw new NotFoundException("该博客不存在");
        }
        Blog b=new Blog();
        BeanUtils.copyProperties(blog,b);
        String content=b.getContent();
        b.setContent( MarkdownUtils.markdownToHtmlExtensions(content));
        // 浏览次数+1
        blogRepository.updateViews(id);
        return b;
    }

    //前台展示标签tag
    @Override
    public Page<Blog> listBlog(Long tagId, Pageable pageable) {

        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                Join join=root.join("tags");
                return cb.equal(join.get("id"),tagId);
            }
        },pageable);
    }

    //推荐博客的实现
    @Override
    public List<Blog> listRecommendBlogTop(Integer size) {
        Sort sort=new Sort(Sort.Direction.DESC,"updateTime");
        Pageable pageable=new PageRequest(0,size,sort);
        return blogRepository.findTop(pageable);
    }

    //归档
    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String> years=blogRepository.findGroupYear();
        Map<String,List<Blog>> map=new HashMap<>();
        for(String year:years){
            map.put(year,blogRepository.findByYear(year));
        }
        return map;
    }

    //新增博客
    @Transactional
    public Blog saveBlog(Blog blog) {
        if(blog.getId()==null){
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        }else{
            blog.setUpdateTime(new Date());
        }

        return blogRepository.save(blog);
    }

    //更新博客
    @Transactional
    public Blog updateBlog(Long id, Blog blog) {
        Blog b=blogRepository.findOne(id);
        if(b==null){
            throw  new NotFoundException("该博客不存在");
        }
        //过滤掉空的
        BeanUtils.copyProperties(blog,b, MyBeanUtils.getNullPropertyNames(blog));
        b.setUpdateTime(new Date());
        return  blogRepository.save(b);
    }

    //删除博客
    @Transactional
    public void deleteBlog(Long id) {
        blogRepository.delete(id);
    }
}

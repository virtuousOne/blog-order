package com.service;

import com.po.Blog;
import com.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @Description: 博客service接口
 * @Author: 吴宸煊
 * @Date: Created in  2017/12/25
 */
public interface BlogService {

    Blog getBlog(Long id); //通过id查询

    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);//分页查询

    Page<Blog> listBlog(Pageable pageable);//前端查询博客

    Page<Blog> listBlog(Pageable pageable, String query);//前端查询博客,search查询

    Blog getAndConvert(Long id);//前台获取博客 并进行文本转换

    Page<Blog> listBlog(Long tagId, Pageable pageable);//前台展示标签

    List<Blog> listRecommendBlogTop(Integer size);  //推荐的博客列表

    Map<String,List<Blog>> archiveBlog(); //归档

    Long countBlog();                    //归档博客的数量

    Blog saveBlog(Blog blog);//新增博客

    Blog updateBlog(Long id, Blog blog);//更新博客

    void deleteBlog(Long id); //删除博客

}

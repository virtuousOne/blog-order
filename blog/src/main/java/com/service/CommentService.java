package com.service;

import com.po.Comment;

import java.util.List;

/**
 * @Description: 评论service接口
 * @Author: 吴宸煊
 * @Date: Created in  2018/3/10 0010
 */
public interface CommentService {

    /**
     *  获取列表
     *@param   blogId
     *@return list
     */
    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);
}

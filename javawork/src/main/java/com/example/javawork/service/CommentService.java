package com.example.javawork.service;

import com.example.javawork.DO.Comment;
import com.example.javawork.DO.ResultInfo;

import java.util.List;

public interface CommentService {
    /**
     * 添加评论
     */
    ResultInfo addComment(Comment comment);

    /**
     * 查找文章第一级评论
     */
    List<Comment> selectByArticleIdAndNoFatherId(int blog_id);

    /**
     * 根据id查找评论
     */
    Comment selectById(int comment_id);

    /**
     *  根据第一级父级id查找
     *  （查找一级评论下的所有子节点）
     */
    List<Comment> selectByFirstFatherId(int first_father_id);
}

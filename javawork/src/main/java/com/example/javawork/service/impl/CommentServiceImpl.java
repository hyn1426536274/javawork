package com.example.javawork.service.impl;

import com.example.javawork.DO.Comment;
import com.example.javawork.DO.ResultInfo;
import com.example.javawork.mapper.CommentMapper;
import com.example.javawork.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public List<Comment> selectByArticleIdAndNoFatherId(int blog_id){
        return commentMapper.selectByArticleIdAndNoFatherId(blog_id);
    }

    @Override
    public ResultInfo addComment(Comment comment){
        commentMapper.insert(comment);
        return ResultInfo.successInfo("添加成功",comment);
    }

    @Override
    public Comment selectById(int comment_id){
        return commentMapper.selectById(comment_id);
    }

    @Override
    public List<Comment> selectByFirstFatherId(int first_father_id) {
        return commentMapper.selectByFirstFatherId(first_father_id);
    }

}

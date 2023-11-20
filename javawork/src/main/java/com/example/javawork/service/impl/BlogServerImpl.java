package com.example.javawork.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.javawork.mapper.BlogMapper;
import com.example.javawork.pojo.Blog;
import com.example.javawork.pojo.ResultInfo;
import com.example.javawork.service.BlogServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Objects;

@Service
public class BlogServerImpl implements BlogServer {
    @Autowired
    BlogMapper blogMapper;
    @Override
    public List<Blog> selectByUserId(int cur_user_id, int tar_user_id){
        if(cur_user_id==tar_user_id){
            return blogMapper.selectByUserId(cur_user_id);
        }
        else return blogMapper.selectByUserIdAndPublished(tar_user_id);
    }

    @Override
    public List<Blog> selectOutlineByUserId(int cur_user_id, int tar_user_id){
        if(cur_user_id==tar_user_id){
            return blogMapper.selectOutlineByUserId(cur_user_id);
        }
        else return blogMapper.selectOutlineByUserIdAndPublished(tar_user_id);
    }

    @Override
    public List<Blog> selectOutlineByTitleAndPublished(String title){
        return blogMapper.selectOutlineByTitleAndPublished(title);
    }



    @Override
    public Blog selectByUserIdAndTitle(int cur_user_id,int tar_user_id, String title){
        if(cur_user_id==tar_user_id){
            // 在自己的个人页查找
            return blogMapper.selectByUserIdAndTitle(cur_user_id,title);
        }
        // 在其他用户的个人页查找
        else return blogMapper.selectByUserIdAndTitleAndPublished(tar_user_id,title);
    }

    @Override
    public Blog selectOutlineByUserIdAndTitle(int cur_user_id,int tar_user_id, String title){
        if(cur_user_id==tar_user_id){
            // 在自己的个人页查找
            return blogMapper.selectOutlineByUserIdAndTitle(cur_user_id,title);
        }
        // 在其他用户的个人页查找
        else return blogMapper.selectOutlineByUserIdAndTitleAndPublished(tar_user_id,title);
    }

    @Override
    public List<Blog> selectOutlineByUsernameAndClass(String cur_username, String tar_username, int class_id){
        if(class_id==0){
            // 查询所有文章
            if(cur_username.equals(tar_username)){
                return blogMapper.selectOutlineByUsername(cur_username);
            }
            else return blogMapper.selectOutlineByUsernameAndPublished(tar_username);
        }
        else {
            if(cur_username.equals(tar_username)){
                return blogMapper.selectOutlineByUsernameAndClass(cur_username,class_id);
            }
            else return blogMapper.selectOutlineByUsernameAndClassAndPublished(tar_username,class_id);
        }
    }

    @Override
    public ResultInfo insert(Blog blog){
        int user_id = blog.getUser_id();
        String title = blog.getTitle();
        if(blogMapper.selectByUserIdAndTitle(user_id,title)==null){
            blogMapper.insert(blog);
            return ResultInfo.successInfo("添加博客成功",blog);
        }
        else return ResultInfo.failInfo("添加失败，您已使用过该文章标题");
    }

    @Override
    public ResultInfo delete(Blog blog){
        int user_id = blog.getUser_id();
        String title = blog.getTitle();
        if(blogMapper.selectByUserIdAndTitle(user_id,title)!=null){
            blogMapper.delete(blog);
            return ResultInfo.successInfo("删除成功",blog);
        }
        else return ResultInfo.failInfo("删除失败，不存在该博客:"+title);
    }

    @Override
    public ResultInfo update(Blog blog){
        blogMapper.update(blog);
        return ResultInfo.successInfo("更新博客成功",blog);
    }
}

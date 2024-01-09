package com.example.javawork.service;

import com.example.javawork.DO.Blog;
import com.example.javawork.DO.ResultInfo;

import java.util.List;

public interface BlogService {
    /**
     * 查找用户的所有博客
     * （判断输入的user_id是否为当前登录的user_id）
     * */
    List<Blog> selectByUserId(int cur_user_id,int tar_user_id);

    /**
     * 查找用户的所有博客【概要】
     * 用于搜索展示
     * （判断输入的user_id是否为当前登录的user_id）
     * */
    List<Blog> selectOutlineByUserId(int cur_user_id,int tar_user_id);

    /**
     * 查找标题为title的文章概要
     * 用于主页搜索
     * 【需要published】
     * 这里应该不需要区分是不是当前用户自身的文章
     * 因为用户查找到所有自己的文章这个功能应该在下一个接口“selectByUserIdAndTitle”
     * */
    List<Blog> selectOutlineByTitleAndPublished(String title);

    /**
     * 根据标题查找文章【进入文章详情页】
     * 用于用户页面（包括其他用户）
     * 这里要区分是不是当前用户
     * */
    Blog selectByUserIdAndTitle(int cur_user_id,int tar_user_id, String title);

    /**
     * 根据标题查找文章概要【搜索】
     * 用于用户页面（包括其他用户）
     * 这里user_id意味着查找别人文章时需要先根据用户名得到用户id？
     * （用用户名要增加用户名唯一）但问题是，j_blog数据表中保存的只有user_id
     * 这里要区分是不是当前用户
     * 有博客可见性问题，如果查找别人的博客还需要其博客为published
     * */
    Blog selectOutlineByUserIdAndTitle(int cur_user_id,int tar_user_id, String title);
    /**
     * 增加博客
     * 一个用户的文章标题唯一
     * 不同用户文章标题可以相同
     * 如果是在主页查找则是根据文章标题查找，可能查出多篇文章
     * */

    List<Blog> selectOutlineByUsernameAndClass(String cur_username,String tar_username,int class_id);

    void addViewsCount(Blog blog);



    ResultInfo insert(Blog blog);

    ResultInfo delete(Blog blog);

    ResultInfo update(Blog blog);

}

package com.example.javawork.controller;

import com.example.javawork.pojo.Blog;
import com.example.javawork.pojo.ResultInfo;
import com.example.javawork.pojo.User;
import com.example.javawork.service.BlogServer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vo.AddBlogParam;
import vo.ChangeBlogParam;

import java.sql.Timestamp;
import java.util.Date;

@RestController
@RequestMapping("/user/blog")
public class BlogController {
    @Autowired
    BlogServer blogServer;
    @PostMapping("/addBlog")
    public ResultInfo addBlog(@RequestBody AddBlogParam param, HttpSession session){
        User user = (User)session.getAttribute("user");
        // 用户需登录
        if(user==null){
            return ResultInfo.failInfo("请先登录");
        }
        int user_id = user.getId();
        String username = user.getUsername();
        String title = param.getTitle();
        String content = param.getContent();
        // 文章标题和内容不能为空
        if(title==null||content==null){
            return ResultInfo.failInfo("文章标题或内容不能为空");
        }
        // 博客初始化
        boolean published = param.isPublished();
        int views = 0; // 阅读数为0
        int comment_count = 0; // 评论数为0
        Date currentDate = new Date();
        Timestamp create_time = new Timestamp(currentDate.getTime());
        Timestamp update_time = new Timestamp(currentDate.getTime());

        // 创建博客对象(全参构造？)
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setDescription(param.getDescription());
        blog.setCreate_time(create_time);
        blog.setUpdate_time(update_time);
        blog.setPublished(published);
        blog.setViews(views);
        blog.setComment_count(comment_count);
        blog.setType_id(param.getType_id());
        blog.setUser_id(user_id);
        blog.setClass_id(param.getClass_id());
        blog.setUsername(username);

        return blogServer.insert(blog);
    }

    @PostMapping("/deleteBlog")
    public ResultInfo deleteBlog(HttpSession session, HttpServletRequest request){
        User user = (User)session.getAttribute("user");
        // 用户需登录
        if(user==null){
            return ResultInfo.failInfo("请先登录");
        }
        int user_id = user.getId();
        String title = request.getParameter("title");
        if(title==null||title.isEmpty()){
            return ResultInfo.failInfo("请输入要删除的文章标题");
        }
        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setUser_id(user_id);

        return blogServer.delete(blog);
    }

    @PostMapping("/updateBlog")
    public ResultInfo updateBlog(@RequestBody ChangeBlogParam param, HttpSession session){
        User user = (User)session.getAttribute("user");
        // 用户需登录
        if(user==null){
            return ResultInfo.failInfo("请先登录");
        }
        int user_id = user.getId();
        String origin_title = param.getOrigin_title();

        Blog tar_blog = blogServer.selectByUserIdAndTitle(user_id,user_id,origin_title);

        if(tar_blog==null) return ResultInfo.failInfo("找不到更改目标");

        String title = param.getTitle();
        String content = param.getContent();
        // 文章标题和内容不能为空
        if(title==null||title.isEmpty()||content==null||content.isEmpty()){
            return ResultInfo.failInfo("文章标题或内容不能为空");
        }
        if(!title.equals(origin_title)){
            // 如果更改了标题，更改后的标题也不应该存在
            Blog find_blog = blogServer.selectByUserIdAndTitle(user_id,user_id,title);
            if(find_blog!=null) return ResultInfo.failInfo("文章标题已存在");
        }

        // 博客参数更改
        Date currentDate = new Date();
        Timestamp update_time = new Timestamp(currentDate.getTime());

        tar_blog.setTitle(title);
        tar_blog.setContent(content);
        tar_blog.setDescription(param.getDescription());
        tar_blog.setUpdate_time(update_time);
        tar_blog.setPublished(param.isPublished());
        tar_blog.setType_id(param.getType_id());
        tar_blog.setClass_id(param.getClass_id());

        return blogServer.update(tar_blog);
    }
}

package com.example.javawork.controller;

import com.example.javawork.DO.Blog;
import com.example.javawork.DO.ResultInfo;
import com.example.javawork.DO.User;
import com.example.javawork.service.BlogService;
import com.example.javawork.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService; // 自动注入，选择其实现类
    @Autowired
    BlogService blogServer;

    @RequestMapping("/findUser")
    public ResultInfo SelectUserById(int id){
        return userService.findUserById(id);
    }

    /**
     * 根据Id查找用户名。主要用在评论模块
     */
    @RequestMapping("/findUsername")
    public ResultInfo SelectUsernameById(int id){
        return userService.findUsernameById(id);
    }
    /**
     * 查找该目录中文章
     * */
    @PostMapping("/class")
    public ResultInfo userClassPage(HttpSession session, HttpServletRequest request){
        User user = (User) session.getAttribute("user");
        String cur_username;
        if(user!=null){
            cur_username = user.getUsername();
        }
        else cur_username = "";

        String tar_username = request.getParameter("username");
        int class_id = Integer.parseInt(request.getParameter("class_id"));
        if(tar_username==null||tar_username.isEmpty()||class_id<0){
            // 更改class_id=0为查找所有文章
            return ResultInfo.failInfo("目标不明确");
        }

        List<Blog> blogList = blogServer.selectOutlineByUsernameAndClass(cur_username,tar_username,class_id);
        if(blogList.isEmpty()){
            return ResultInfo.failInfo("未找到文章");
        }
        else return ResultInfo.successInfo("查找文章成功",blogList);
    }

    /**
     * 访问文章详情页
     * */
    @PostMapping("/article")
    public ResultInfo enterArticlePage(HttpSession session,HttpServletRequest request){
        User user = (User) session.getAttribute("user");
        int cur_user_id;
        if(user==null){
            cur_user_id = 0;
        }else cur_user_id = user.getId();

        int tar_user_id = Integer.parseInt(request.getParameter("user_id"));
        String title = request.getParameter("title");
        if(tar_user_id > 0 && title!=null && !title.isEmpty()){
            Blog blog = blogServer.selectByUserIdAndTitle(cur_user_id,tar_user_id,title);
            if(blog!=null){
                // 增加浏览量
                blogServer.addViewsCount(blog);
                return ResultInfo.successInfo("访问成功",blog);
            }
            else {
                Blog blog1 = new Blog();
                blog1.setTitle("访问错误");
                blog1.setContent("可能是文章不存在或没有权限访问");
                return ResultInfo.failInfo("访问错误",blog1);
            }
        }
        else return ResultInfo.failInfo("目标错误");
    }
}
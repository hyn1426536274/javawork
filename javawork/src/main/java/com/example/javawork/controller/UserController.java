package com.example.javawork.controller;

import com.example.javawork.pojo.Blog;
import com.example.javawork.pojo.BlogClass;
import com.example.javawork.pojo.ResultInfo;
import com.example.javawork.pojo.User;
import com.example.javawork.service.BlogServer;
import com.example.javawork.service.ClassService;
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
    BlogServer blogServer;

    @RequestMapping("/findUser")
    public ResultInfo SelectUserById(int id){
        return userService.findUserById(id);
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
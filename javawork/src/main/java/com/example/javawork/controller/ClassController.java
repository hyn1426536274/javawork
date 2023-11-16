package com.example.javawork.controller;

import com.example.javawork.pojo.BlogClass;
import com.example.javawork.pojo.ResultInfo;
import com.example.javawork.pojo.User;
import com.example.javawork.service.ClassService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;


/**
 * 用户文章类别目录控制类
 * */
@RestController
@RequestMapping("/user")
public class ClassController {

    @Autowired
    ClassService classService;
    @RequestMapping("/getClasses")
    public ResultInfo selectClassesByUserid(HttpSession session){
        User user = (User) session.getAttribute("user");

        if(user==null) return ResultInfo.failInfo("请先登录");

        List<BlogClass> blogClasses= classService.selectClassesByUserid(user.getId());
        if(!blogClasses.isEmpty()){
            return ResultInfo.successInfo("获取文章目录成功",blogClasses);
        }
        else return ResultInfo.failInfo("目录列表为空,user_id="+user.getId());
    }

    /**
     * 添加文章类别目录
     * */
    // 新建会话使用 HttpServletRequest : request.getSession()
    // 添加，获得属性使用 HttpSession
    @PostMapping("/addClass")
    public ResultInfo addClass(HttpSession session, HttpServletRequest request){
        User user = (User) session.getAttribute("user");
        String name = request.getParameter("name");
        if(user==null) return ResultInfo.failInfo("请先登录");
        if(Objects.equals(name, "")) return ResultInfo.failInfo("目录名不能为空");

        BlogClass blogClass = new BlogClass();
        blogClass.setName(name);
        blogClass.setUser_id(user.getId());

        return classService.addClass(blogClass);
    }

    @PostMapping("/deleteClass")
    public ResultInfo deleteClass(HttpSession session,HttpServletRequest request){
        User user = (User) session.getAttribute("user");
        String name = request.getParameter("name");
        if(user==null) return ResultInfo.failInfo("请先登录");
        if(Objects.equals(name, "")) return ResultInfo.failInfo("目录名不能为空");

        BlogClass blogClass = new BlogClass();
        blogClass.setName(request.getParameter("name"));
        blogClass.setUser_id(user.getId());

        return classService.deleteClass(blogClass);
    }

    @PostMapping("/findClass")
    public ResultInfo findClass(HttpSession session,HttpServletRequest request){
        User user = (User) session.getAttribute("user");
        String name = request.getParameter("name");
        if(user==null) return ResultInfo.failInfo("请先登录");
        if(Objects.equals(name, "")) return ResultInfo.failInfo("目录名不能为空");

        BlogClass blogClass = classService.selectClassesByUseridAndName(user.getId(), request.getParameter("name"));
        if(blogClass!=null){
            return ResultInfo.successInfo("查询文章目录成功",blogClass);
        }
        else return ResultInfo.failInfo("查询文章目录失败，目录可能不存在");
    }
}

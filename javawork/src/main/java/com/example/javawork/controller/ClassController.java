package com.example.javawork.controller;

import com.example.javawork.DO.BlogClass;
import com.example.javawork.DO.ResultInfo;
import com.example.javawork.DO.User;
import com.example.javawork.service.ClassService;
import com.example.javawork.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 用户文章类别目录控制类
 * */
@RestController
@RequestMapping("/user")
public class ClassController {

    @Autowired
    ClassService classService;
    @Autowired
    UserService userService;
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
     * 进入用户页面时查询其目录
     * */
    @PostMapping("/getUserClasses")
    public ResultInfo selectClassesByUsername(HttpServletRequest request){
        String username = request.getParameter("username");

        if(username==null||username.isEmpty()){
            return ResultInfo.failInfo("目标用户错误");
        }
        ResultInfo info = userService.findIdByUsername(username);
        if(info.getStatus()==0){
            return ResultInfo.failInfo(info.getMessage());
        }

        int tar_user_id = (int) info.getData();
        List<BlogClass> blogClassList= classService.selectClassesByUserid(tar_user_id);
        if(!blogClassList.isEmpty()){
            return ResultInfo.successInfo("查询用户文章目录成功",blogClassList);
        }
        else return ResultInfo.failInfo("该用户还没有文章目录");
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
        if(name==null||name.isEmpty()) return ResultInfo.failInfo("目录名不能为空");

        BlogClass blogClass = new BlogClass();
        blogClass.setName(name);
        blogClass.setUser_id(user.getId());

        return classService.addClass(blogClass);
    }

    /**
     * 删除文章类别目录
     * */
    @PostMapping("/deleteClass")
    public ResultInfo deleteClass(HttpSession session,HttpServletRequest request){
        User user = (User) session.getAttribute("user");
        String name = request.getParameter("name");
        if(user==null) return ResultInfo.failInfo("请先登录");
        if(name==null||name.isEmpty()) return ResultInfo.failInfo("目录名不能为空");

        BlogClass blogClass = new BlogClass();
        blogClass.setName(request.getParameter("name"));
        blogClass.setUser_id(user.getId());

        return classService.deleteClass(blogClass);
    }


//    // 暂时没用？
//    @PostMapping("/findClass")
//    public ResultInfo findClass(HttpSession session,HttpServletRequest request){
//        User user = (User) session.getAttribute("user");
//        String name = request.getParameter("name");
//        if(user==null) return ResultInfo.failInfo("请先登录");
//        if(Objects.equals(name, "")) return ResultInfo.failInfo("目录名不能为空");
//
//        BlogClass blogClass = classService.selectClassesByUseridAndName(user.getId(), request.getParameter("name"));
//        if(blogClass!=null){
//            return ResultInfo.successInfo("查询文章目录成功",blogClass);
//        }
//        else return ResultInfo.failInfo("查询文章目录失败，目录可能不存在");
//    }
}

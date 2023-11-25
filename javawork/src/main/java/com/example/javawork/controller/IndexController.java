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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.javawork.vo.UserPageParam;

import java.util.List;
import java.util.Objects;

@RestController
public class IndexController {
    @Autowired
    BlogServer blogServer;
    @Autowired
    ClassService classService;
    @Autowired
    UserService userService;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    /**
     * （进入）用户主页
     */
    @PostMapping("/user")
    ResultInfo userPage(HttpSession session, HttpServletRequest request){
        User user = (User) session.getAttribute("user");
        String tar_username = request.getParameter("username");

        if(tar_username==null){
            // 没有输入目标访问用户
            if(user!=null){
                // 已登录，进入自己主页
                int user_id = user.getId();
                List<Blog> blogList = blogServer.selectOutlineByUserId(user_id,user_id);
                List<BlogClass> classList = classService.selectClassesByUserid(user_id);
                boolean is_self = true;

                UserPageParam userPageParam = new UserPageParam(
                        is_self,
                        user,
                        classList,
                        blogList
                );

                return ResultInfo.successInfo("成功访问用户",userPageParam);
            }
            else return ResultInfo.failInfo("请输入目标用户");
        }
        else {
            // 输入了目标访问用户
            if(user!=null){
                // 已登录，区分是否是自己主页
                String cur_username = user.getUsername();
                int cur_user_id = user.getId();
                if(Objects.equals(cur_username, tar_username)){
                    // 是访问自己个人主页
                    List<Blog> blogList = blogServer.selectOutlineByUserId(cur_user_id,cur_user_id);
                    List<BlogClass> classList = classService.selectClassesByUserid(cur_user_id);
                    boolean is_self = true;

                    UserPageParam userPageParam = new UserPageParam(
                            is_self,
                            user,
                            classList,
                            blogList
                    );

                    return ResultInfo.successInfo("成功访问用户",userPageParam);
                }
                else {
                    // 是访问其他用户
                    ResultInfo userinfo = userService.findUserByUsername(tar_username);
                    if(userinfo.getStatus()==0) return ResultInfo.failInfo("访问失败");

                    User tar_user = (User) userinfo.getData();
                    int tar_user_id = tar_user.getId();
                    List<Blog> blogList = blogServer.selectOutlineByUserId(cur_user_id,tar_user_id);
                    List<BlogClass> classList = classService.selectClassesByUserid(tar_user_id);
                    boolean is_self = false;

                    // 访问其他用户，只能获取非隐私信息
                    // 允许获得用户名，邮箱，描述
                    User user1 = new User();
                    user1.setUsername(tar_user.getUsername());
                    user1.setEmail(tar_user.getEmail());
                    user1.setDescription(tar_user.getDescription());

                    UserPageParam userPageParam = new UserPageParam(
                            is_self,
                            user1,
                            classList,
                            blogList
                    );

                    return ResultInfo.successInfo("成功访问用户",userPageParam);
                }
            }
            else {
                // 未登录，不做区分，访问其他用户
                int cur_user_id = 0;
                // 是访问其他用户
                ResultInfo userinfo = userService.findUserByUsername(tar_username);
                if(userinfo.getStatus()==0) return ResultInfo.failInfo("访问失败");

                User tar_user = (User) userinfo.getData();
                int tar_user_id = tar_user.getId();
                List<Blog> blogList = blogServer.selectOutlineByUserId(cur_user_id,tar_user_id);
                List<BlogClass> classList = classService.selectClassesByUserid(tar_user_id);
                boolean is_self = false;

                // 访问其他用户，只能获取非隐私信息
                // 允许获得用户名，邮箱，描述
                User user1 = new User();
                user1.setUsername(tar_user.getUsername());
                user1.setEmail(tar_user.getEmail());
                user1.setDescription(tar_user.getDescription());

                UserPageParam userPageParam = new UserPageParam(
                        is_self,
                        user1,
                        classList,
                        blogList
                );

                return ResultInfo.successInfo("成功访问用户",userPageParam);
           }
        }
    }


    /**
     * 首页搜索
     *
     */
    @PostMapping("/index/search")
    ResultInfo indexSearch(HttpServletRequest request){
        String title = request.getParameter("title");
        if(title==null||title.isEmpty()) return ResultInfo.failInfo("请输入查找内容");

        List<Blog> blogList= blogServer.selectOutlineByTitleAndPublished(title);
        if(blogList.isEmpty()){
            return ResultInfo.failInfo("未找到文章");
        }
        else return ResultInfo.successInfo("查找文章成功",blogList);
    }

}

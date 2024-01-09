package com.example.javawork.controller;

import com.example.javawork.DO.Blog;
import com.example.javawork.DO.Comment;
import com.example.javawork.DO.ResultInfo;
import com.example.javawork.DO.User;
import com.example.javawork.service.BlogService;
import com.example.javawork.service.CommentService;
import com.example.javawork.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/user/article")
public class CommentContrller {
    @Autowired
    CommentService commentService;
    @Autowired
    BlogService blogService;
    @Autowired
    UserService userService;

    @PostMapping("getCommentById")
    public ResultInfo selectById(HttpServletRequest request){
        int tar_comment_id = Integer.parseInt(request.getParameter("tar_comment_id"));
        Comment comment = commentService.selectById(tar_comment_id);
        if(comment==null){
            return ResultInfo.failInfo("未找到相应评论");
        }
        return ResultInfo.successInfo("查找成功",comment);
    }

    @PostMapping("getFirstComments")
    public ResultInfo selectByArticleIdAndNoFatherId(HttpSession session,HttpServletRequest request)
    {
        String title = request.getParameter("title");
        String authorname = request.getParameter("authorname");
        int cur_user_id = 0; // 查看评论无需登录
        User user = (User) session.getAttribute("user");
        if(user!=null){
            // 用于查看还未发布的自己的文章的评论
            cur_user_id = user.getId();
        }
        if(title==null||authorname==null||title.isEmpty()||authorname.isEmpty()){
            return ResultInfo.failInfo("文章目标错误，可能是文章标题或用户名无效");
        }
        ResultInfo resultInfo = userService.findIdByUsername(authorname);
        if(resultInfo.getStatus()==0){
            return ResultInfo.failInfo("未知作者");
        }
        int tar_user_id = (int) resultInfo.getData();

        Blog blog = blogService.selectByUserIdAndTitle(cur_user_id,tar_user_id,title);
        if(blog==null){
            return ResultInfo.failInfo("访问失败，可能是没有权限或文章不存在");
        }
        int tar_blog_id = blog.getId();

        if(!(tar_blog_id>0)){
            return ResultInfo.failInfo("目标格式错误");
        }
        List<Comment> comments = commentService.selectByArticleIdAndNoFatherId(tar_blog_id);
        if(!comments.isEmpty()){
            return ResultInfo.successInfo("查找成功",comments);
        }
        return ResultInfo.failInfo("未找到相应评论");
    }
    @PostMapping("getChildComments")
    public ResultInfo selectByFirstFatherId(HttpServletRequest request){
        int first_father_id = Integer.parseInt(request.getParameter("first_father_id"));
        List<Comment> childComments = commentService.selectByFirstFatherId(first_father_id);
        if(childComments==null||childComments.isEmpty()){
            return ResultInfo.failInfo("未找到子评论");
        }
        return ResultInfo.successInfo("查找子评论成功",childComments);
    }



    @PostMapping("replyToArticle")
    public ResultInfo addCommentToArticle(HttpSession session,HttpServletRequest request){
        User user = (User)session.getAttribute("user");
        String title = request.getParameter("title");
        String authorname = request.getParameter("authorname");
        String content = request.getParameter("content");
        // 用户需登录
        if(user==null){
            return ResultInfo.failInfo("请先登录");
        }
        if(content==null||content.isEmpty()){
            return ResultInfo.failInfo("请输入评论内容");
        }
        if(title==null||authorname==null||title.isEmpty()||authorname.isEmpty()){
            return ResultInfo.failInfo("文章目标错误，可能是文章标题或用户名无效");
        }
        int cur_user_id = user.getId();
        ResultInfo resultInfo = userService.findIdByUsername(authorname);
        if(resultInfo.getStatus()==0){
            return ResultInfo.failInfo("未知作者");
        }
        int tar_user_id = (int) resultInfo.getData();
        Blog blog = blogService.selectByUserIdAndTitle(cur_user_id,tar_user_id,title);
        if(blog==null){
            return ResultInfo.failInfo("访问失败，可能是没有权限或文章不存在");
        }
        // 评论信息初始化
        int tar_blog_id = blog.getId();
        Date currentDate = new Date();
        Timestamp create_time = new Timestamp(currentDate.getTime());
        int first_father_id = 0;
        int near_father_id = 0;
        String username = user.getUsername();    // 评论者用户名
        int approvals = 0; // 点赞数为0
        boolean is_deleted = false;

        // 创建评论对象
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCreate_time(create_time);
        comment.setBlog_id(tar_blog_id);
        comment.setFirst_father_id(first_father_id);
        comment.setNear_father_id(near_father_id);
        comment.setOwner_id(cur_user_id);
        comment.setUsername(username);
        comment.setApprovals(approvals);
        comment.set_deleted(is_deleted);

        ResultInfo resultInfo1 = commentService.addComment(comment);
        if(resultInfo1.getStatus()==1){
            return ResultInfo.successInfo("添加评论成功",comment);
        }
        return ResultInfo.failInfo("添加评论失败");
    }

    /**
     * 回复评论
     */
    @PostMapping("replyToComment")
    public ResultInfo addCommentToComment(HttpSession session ,HttpServletRequest request){
        User user = (User)session.getAttribute("user");
        String content = request.getParameter("content");
        int tar_blog_id = Integer.parseInt(request.getParameter("tar_blog_id"));
        // 父评论Id
        int near_father_id = Integer.parseInt(request.getParameter("near_father_id"));
        // 用户需登录
        if(user==null){
            return ResultInfo.failInfo("请先登录");
        }
        if(content==null||content.isEmpty()){
            return ResultInfo.failInfo("请输入评论内容");
        }
        int cur_user_id = user.getId();

        //----------------------
        Comment tar_comment = commentService.selectById(near_father_id);
        if(tar_comment==null){
            return ResultInfo.failInfo("错误，未找到目标评论");
        }
        int first_father_id;
        if(tar_comment.getFirst_father_id()==0&&tar_comment.getNear_father_id()==0){
            // 目标父评论为一级评论
            first_father_id = near_father_id;
        }
        else{
            first_father_id = tar_comment.getFirst_father_id();
        }
        //----------------------

        Date currentDate = new Date();
        Timestamp create_time = new Timestamp(currentDate.getTime());
        String username = user.getUsername();    // 评论者用户名
        int approvals = 0; // 点赞数为0
        boolean is_deleted = false;
        // 创建评论对象
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCreate_time(create_time);
        comment.setBlog_id(tar_blog_id);
        comment.setFirst_father_id(first_father_id);
        comment.setNear_father_id(near_father_id);
        comment.setOwner_id(cur_user_id);
        comment.setUsername(username);
        comment.setApprovals(approvals);
        comment.set_deleted(is_deleted);

        ResultInfo resultInfo1 = commentService.addComment(comment);
        if(resultInfo1.getStatus()==1){
            return ResultInfo.successInfo("添加评论成功",comment);
        }
        return ResultInfo.failInfo("添加评论失败");
    }


}

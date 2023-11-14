package com.example.javawork.controller;

import com.example.javawork.pojo.ResultInfo;
import com.example.javawork.pojo.ToEmail;
import com.example.javawork.pojo.User;
import com.example.javawork.service.EmailService;
import com.example.javawork.service.UserService;
import com.example.javawork.utils.VerCodeGenerator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    EmailService emailService;

    // 获得发送人信息
    @Value("${spring.mail.username}")
    private String from;
    @PostMapping("/login")
    public ResultInfo login(HttpServletRequest request){
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        return userService.login(username,password);
    }

    // @RequestBody 接收前端Json格式请求体
    // HttpServletRequest 接收前端传递的表单数据
    /**
     * 只发送邮件，注册功能在验证中完成
     * */
    @PostMapping("/register")
    public ResultInfo register(HttpServletRequest request){
        String verCode = VerCodeGenerator.generateVerCode();
        // 创建邮件对象
        ToEmail toEmail = new ToEmail();
        toEmail.setTos(request.getParameter("email").split(","));
        toEmail.setContent(verCode);

        // 将验证码存储在 HttpSession 中，使用邮箱作为标识
        HttpSession session = request.getSession();
        session.setAttribute(request.getParameter("email"),verCode);
        return emailService.sendVerifyCode(toEmail,from);
    }

    @RequestMapping("/verifyCode")
    public ResultInfo verifyCode(HttpServletRequest request){
        String email = request.getParameter("email");
        String userCode = request.getParameter("code");

        // request.getSession() 默认不存在session则会新建一个返回，使用false不存在则返回null，但要验证
        HttpSession session = request.getSession(false);
        if(session == null){
            return ResultInfo.failInfo("验证码未发送");
        }
        // getAttribute 返回Object类型
        String trueCode = (String) session.getAttribute(email);
        if(userCode.equals(trueCode)){
            // 创建user对象并注册
            User user = new User();
            user.setUsername(request.getParameter("username"));
            user.setPassword(request.getParameter("password"));
            user.setEmail(email);
            return userService.registration(user);
        }
        else return ResultInfo.failInfo("验证码错误");
    }
}

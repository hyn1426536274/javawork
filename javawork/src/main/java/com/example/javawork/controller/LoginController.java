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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vo.LoginParam;

@RestController
@RequestMapping("/admin")
public class LoginController {
    @Autowired
    UserService userService;
    @Autowired
    EmailService emailService;

    // 获得发送人信息
    @Value("${spring.mail.username}")
    private String from;

    // @RequestBody 接收前端Json格式请求体(Body/raw)
    // HttpServletRequest 接收前端传递的表单数据
    @PostMapping("/login")
    public ResultInfo login(@RequestBody LoginParam param,HttpSession session){
        //        String username = request.getParameter("username");
        //        String password = request.getParameter("password");
        String username = param.getUsername();
        String password = param.getPassword();

        ResultInfo info = userService.login(username,password);
        User user = (User) info.getData();
        // 如果登录成功会返回用户对象
        if(user!=null){
            // 后续可添加在线信息，
            session.setAttribute("user",user);
            return ResultInfo.successInfo("登录成功");
        }
        else return ResultInfo.failInfo(info.getMessage());
    }
    @RequestMapping("/isLogin")
    public ResultInfo isLogin(HttpSession session){
        User user = (User)session.getAttribute("user");
        if(user!=null){
            return ResultInfo.successInfo("用户"+user.getUsername()+"已登录",user);
        }
        else return ResultInfo.failInfo("用户未登录");
    }

    @RequestMapping("/loginOut")
    public ResultInfo loginOut(HttpSession session){
        User user = (User) session.getAttribute("user");
        // session的作用域在会话期间，一个用户访问服务器就有一个session，用户调用的是他的session
        if(user!=null){
            // 直接删除该session，而不是清除某些属性
            session.invalidate();
            return ResultInfo.successInfo("成功退出");
        }
        else return ResultInfo.failInfo("当前没有用户登录");
    }

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

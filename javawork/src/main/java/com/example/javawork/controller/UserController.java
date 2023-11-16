package com.example.javawork.controller;

import com.example.javawork.pojo.BlogClass;
import com.example.javawork.pojo.ResultInfo;
import com.example.javawork.pojo.User;
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

    @RequestMapping("/findUser")
    public User SelectUserById(int id){
        return userService.findById(id);
    }

}

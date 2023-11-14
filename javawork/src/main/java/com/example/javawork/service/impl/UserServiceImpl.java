package com.example.javawork.service.impl;

import com.example.javawork.mapper.UserMapper;
import com.example.javawork.pojo.ResultInfo;
import com.example.javawork.pojo.User;
import com.example.javawork.service.UserService;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.SplittableRandom;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User findById(int id) {
        return userMapper.selectById(id);
    }

    @Override
    public ResultInfo repeatUsername(String username){
        if(userMapper.selectByUsername(username.strip())!=null){
            return ResultInfo.failInfo("用户名存在");
        }
        else return ResultInfo.successInfo("用户名可用");
    }


    @Override
    public ResultInfo registration(User user){
        if(repeatUsername(user.getUsername()).getStatus()==1){
            userMapper.insert(user);
            return ResultInfo.successInfo("注册成功");
        }
        else return ResultInfo.failInfo("注册失败，可能是用户名重复");
    }

    @Override
    public ResultInfo login(String username, String password){
        if(userMapper.selectByUsername(username.strip())==null)
            return ResultInfo.failInfo("用户不存在");

        User user = userMapper.selectByUsername(username.strip());
        // 使用equals而不是==来比较String
        if(Objects.equals(user.getPassword(), password))
            // 返回data = user对象
            return ResultInfo.successInfo("登录成功",user);
        else return ResultInfo.failInfo("请检查用户名和密码");
    }

    @Override
    public ResultInfo updateDescription(String username, String description){
        userMapper.updateDescription(username,description);
        return ResultInfo.successInfo("更改成功");
    }
}

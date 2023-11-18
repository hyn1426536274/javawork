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
    public ResultInfo findUserById(int id) {
        User user= userMapper.selectById(id);
        if(user!=null){
            return ResultInfo.successInfo("查找用户成功",user);
        }
        else return ResultInfo.failInfo("找不到目标用户");
    }

    @Override
    public ResultInfo findUserByUsername(String username) {
        User user= userMapper.selectByUsername(username);
        if(user!=null){
            return ResultInfo.successInfo("查找用户成功",user);
        }
        else return ResultInfo.failInfo("找不到目标用户");
    }

    @Override
    public ResultInfo findIdByUsername(String username){
        int id = userMapper.selectIdByUsername(username);
        if(id>0){
            return ResultInfo.successInfo("查找id成功",id);
        }
        else return ResultInfo.failInfo("找不到用户:"+username);
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
            return ResultInfo.successInfo("登录成功"+user,user);
        else return ResultInfo.failInfo("请检查用户名和密码");
    }

    @Override
    public ResultInfo updateDescription(String username, String description){
        userMapper.updateDescription(username,description);
        return ResultInfo.successInfo("更改成功");
    }
}

package com.example.javawork.service;

import com.example.javawork.pojo.ResultInfo;
import com.example.javawork.pojo.User;

public interface UserService {
    /**
     * 根据Id查找用户名
     */
    public User findById(int id);

    /**
     * 查询是否有重复的用户名
     */
    public ResultInfo repeatUsername(String username);


    /**
     * 注册接口
     * */
    public ResultInfo registration(User user);

    /**
     * 登录接口
     * 待实现：可能有多种登录方式，传入数据应为HashMap
     * 可能要修改在线状态等
     * */
    public ResultInfo login(String username, String password);

    /**
     * 更改用户描述
     * */
    public ResultInfo updateDescription(String username,String description);
}

package com.example.javawork.service;

import com.example.javawork.DO.ResultInfo;
import com.example.javawork.DO.User;

public interface UserService {
    /**
     * 根据Id查找用户
     * (获得用户的全部信息)
     * 应该用于查找自身（如自身个人页）
     */
    public ResultInfo findUserById(int id);

    /**
     * 根据username查找用户
     * */
    public ResultInfo findUserByUsername(String username);
    /**
     * 根据用户名获取用户id
     * */
    public ResultInfo findIdByUsername(String username);
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

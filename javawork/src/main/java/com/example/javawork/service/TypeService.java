package com.example.javawork.service;

import com.example.javawork.pojo.Type;

import java.util.List;

public interface TypeService {
    /**
     * 获取所有文章类别
     * */
    public List<Type> getAllType();

    /**
     * 根据类别id号获取文章类别
     * */
    public Type selectById(int id);
}

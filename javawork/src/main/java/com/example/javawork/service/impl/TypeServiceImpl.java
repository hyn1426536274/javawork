package com.example.javawork.service.impl;

import com.example.javawork.mapper.TypeMapper;
import com.example.javawork.pojo.ResultInfo;
import com.example.javawork.pojo.Type;
import com.example.javawork.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeMapper typeMapper;

    @Override
    public List<Type> getAllType(){return typeMapper.selectAll();}

    @Override
    public Type selectById(int id){return typeMapper.selectById(id);}
}

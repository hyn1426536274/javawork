package com.example.javawork.mapper;

import com.example.javawork.DO.Type;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TypeMapper {
    @Select("select * from j_type")
    List<Type> selectAll();

    @Select("select * from j_type where id=#{id}")
    Type selectById(int id);
}

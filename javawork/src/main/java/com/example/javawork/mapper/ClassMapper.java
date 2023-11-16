package com.example.javawork.mapper;

import com.example.javawork.pojo.BlogClass;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 用户文章目录管理
 * 关联user_id
 * */
@Mapper
public interface ClassMapper {

    @Select("select * from j_class where user_id=#{user_id}")
    List<BlogClass> selectByUserid(int user_id);

    @Select("select * from j_class where user_id=#{user_id} " +
            "and name=#{name}")
    BlogClass selectByUseridAndName(int user_id,String name);

    // 自动获取参数填充
    @Insert("insert into j_class (name,user_id)"+
    "values (#{name},#{user_id})")
    void insert(BlogClass blogClass);

    @Delete("delete from j_class where user_id=#{user_id} " +
            "and name=#{name}")
    void delete(BlogClass blogClass);
}

package com.example.javawork.mapper;

import com.example.javawork.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper{
    // Mapper 仅仅完成基本操作，返回状态信息等交由impl的集成操作完成
    @Select("select * from user where id = #{id}")
    User selectById(int id);

    @Select("select * from user where username = #{username}")
    User selectByUsername(String username);

    @Select("select id from user where username = #{username}")
    int selectIdByUsername(String username);

    @Insert("INSERT INTO user (username, password,email) " +
            "VALUES (#{username},#{password},#{email})")
    void insert(User user);


    @Update("update user set description=#{description} where username=#{username}")
    void updateDescription(String username,String description);
}

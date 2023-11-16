package com.example.javawork.mapper;

import com.example.javawork.pojo.Blog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BlogMapper {
    @Select("select * from j_blog where user_id=#{user_id}")
    List<Blog> selectByUserId(int user_id);

    @Select("select * from j_blog where user_id=#{user_id} " +
            "and title=#{title}")
    Blog selectByUserIdAndTitle(int user_id, String title);

    @Insert("insert into j_blog (title, content, description, create_time, update_time, published, views, comment_count, type_id, user_id) " +
            "values (#{title}, #{content}, #{description}, #{create_time}, #{update_time}, #{published}, #{views}, #{comment_count}, #{type_id}, #{user_id})")
    void insert(Blog blog);

    @Delete("delete from j_blog where user_id=#{user_id} " +
            "and title=#{title}")
    void delete(Blog blog);
}

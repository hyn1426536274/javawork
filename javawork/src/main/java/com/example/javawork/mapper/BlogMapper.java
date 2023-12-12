package com.example.javawork.mapper;

import com.example.javawork.DO.Blog;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BlogMapper {
    @Select("select * from j_blog where user_id=#{user_id}")
    List<Blog> selectByUserId(int user_id);

    @Select("select * from j_blog where title=#{title}")
    List<Blog> selectByTitle(String title);

    @Select("select * from j_blog where user_id=#{user_id} " +
            "and published=true")
    List<Blog> selectByUserIdAndPublished(int user_id);

    @Select("select * from j_blog where user_id=#{user_id} " +
            "and title=#{title}")
    Blog selectByUserIdAndTitle(int user_id, String title);

    @Select("select * from j_blog where user_id=#{user_id} " +
            "and title=#{title} " +
            "and published=true")
    Blog selectByUserIdAndTitleAndPublished(int user_id, String title);

    @Select("select title,description,create_time,update_time,published,views,comment_count,type_id,user_id,class_id,username" +
            " from j_blog where user_id=#{user_id}")
    List<Blog> selectOutlineByUserId(int user_id);
    @Select("select title,description,create_time,update_time,published,views,comment_count,type_id,user_id,class_id,username" +
            " from j_blog where user_id=#{user_id} " +
            "and published=true")
    List<Blog> selectOutlineByUserIdAndPublished(int user_id);

    @Select("select title,description,create_time,update_time,published,views,comment_count,type_id,user_id,class_id,username" +
            " from j_blog where username=#{username}")
    List<Blog> selectOutlineByUsername(String username);
    @Select("select title,description,create_time,update_time,published,views,comment_count,type_id,user_id,class_id,username" +
            " from j_blog where username=#{username} " +
            "and published=true")
    List<Blog> selectOutlineByUsernameAndPublished(String username);

    @Select("select title,description,create_time,update_time,published,views,comment_count,type_id,user_id,class_id,username" +
            " from j_blog where username=#{username} " +
            "and class_id=#{class_id}")
    List<Blog> selectOutlineByUsernameAndClass(String username,int class_id);
    @Select("select title,description,create_time,update_time,published,views,comment_count,type_id,user_id,class_id,username" +
            " from j_blog where username=#{username} " +
            "and class_id=#{class_id} " +
            "and published=true")
    List<Blog> selectOutlineByUsernameAndClassAndPublished(String username,int class_id);

    @Select("select title,description,create_time,update_time,published,views,comment_count,type_id,user_id,class_id,username" +
            " from j_blog where title like '%${title}%' " +
            "and published=true")
    List<Blog> selectOutlineByTitleAndPublished(String title);


    @Select("select title,description,create_time,update_time,published,views,comment_count,type_id,user_id,class_id,username" +
            " from j_blog where user_id=#{user_id} " +
            "and title=#{title}")
    Blog selectOutlineByUserIdAndTitle(int user_id, String title);

    @Select("select title,description,create_time,update_time,published,views,comment_count,type_id,user_id,class_id,username" +
            " from j_blog where user_id=#{user_id} " +
            "and title=#{title} " +
            "and published=true")
    Blog selectOutlineByUserIdAndTitleAndPublished(int user_id, String title);



    @Insert("insert into j_blog (title, content, description, create_time, " +
            "update_time, published, views, comment_count, " +
            "type_id, user_id,class_id,username) " +
            "values (#{title}, #{content}, #{description}, #{create_time}, " +
            "#{update_time}, #{published}, #{views}, #{comment_count}, " +
            "#{type_id}, #{user_id},#{class_id}, #{username})")
    void insert(Blog blog);

    @Update("UPDATE j_blog SET " +
            "title = #{title}, " +
            "content = #{content}, " +
            "description = #{description}, " +
            "update_time = #{update_time}, " +
            "published = #{published}, " +
            "type_id = #{type_id}, " +
            "class_id = #{class_id} " +
            "WHERE id = #{id}")
    void update(Blog blog);


    @Delete("delete from j_blog where user_id=#{user_id} " +
            "and title=#{title}")
    void delete(Blog blog);

}

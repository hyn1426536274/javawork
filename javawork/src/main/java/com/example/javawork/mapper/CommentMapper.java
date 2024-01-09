package com.example.javawork.mapper;

import com.example.javawork.DO.Blog;
import com.example.javawork.DO.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;

@Mapper
public interface CommentMapper {
    @Insert("insert into j_comment (content, create_time, blog_id, first_father_id, " +
            "near_father_id, owner_id, username, approvals, " +
            "is_deleted )" +
            "values (#{content}, #{create_time}, #{blog_id}, #{first_father_id}, " +
            "#{near_father_id}, #{owner_id}, #{username}, #{approvals}, " +
            "#{is_deleted})")
    void insert(Comment comment);

    @Select("select * " +
            "from j_comment where blog_id=#{blog_id} " +
            "AND first_father_id = 0 ")
    List<Comment> selectByArticleIdAndNoFatherId(int article_id);

    @Select("select * " +
            "from j_comment where id=#{comment_id}")
    Comment selectById(int comment_id);

    @Select("select * from j_comment where first_father_id = #{first_father_id};")
    List<Comment> selectByFirstFatherId(int first_father_id);
}

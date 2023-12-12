package com.example.javawork.VO;

import com.example.javawork.DO.Blog;
import com.example.javawork.DO.BlogClass;
import com.example.javawork.DO.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 用户主页信息
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPageParam implements Serializable {
    private boolean is_self;
    private User user;
    private List<BlogClass> blogClassList;
    private List<Blog> blogList;
}

package com.example.javawork.service;

import com.example.javawork.DO.BlogClass;
import com.example.javawork.DO.ResultInfo;

import java.util.List;

public interface ClassService {

    List<BlogClass> selectClassesByUserid(int user_id);

    ResultInfo addClass(BlogClass blogClass);

    ResultInfo deleteClass(BlogClass blogClass);

    BlogClass selectClassesByUseridAndName(int user_id, String name);
}

package com.example.javawork.service.impl;

import com.example.javawork.mapper.ClassMapper;
import com.example.javawork.DO.BlogClass;
import com.example.javawork.DO.ResultInfo;
import com.example.javawork.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    ClassMapper classMapper;
    @Override
    public List<BlogClass> selectClassesByUserid(int user_id){return classMapper.selectByUserid(user_id);}



    @Override
    public ResultInfo addClass(BlogClass blogClass){
        if(blogClass.getName()!=null){
            BlogClass blogClass1 = classMapper.selectByUseridAndName(blogClass.getUser_id(),blogClass.getName());
            if(blogClass1!=null){
                return ResultInfo.failInfo("添加失败，目录已存在");
            }
            classMapper.insert(blogClass);
            return ResultInfo.successInfo("添加目录成功");
        }
        else return ResultInfo.failInfo("添加失败，可能是类别名为空");
    }

    @Override
    public ResultInfo deleteClass(BlogClass blogClass){
        if(blogClass.getName()!=null && blogClass.getUser_id()>0){
            BlogClass blogClass1 = classMapper.selectByUseridAndName(blogClass.getUser_id(),blogClass.getName());
            if(blogClass1==null){
                return ResultInfo.failInfo("目录不存在");
            }
            classMapper.delete(blogClass1);
            return ResultInfo.successInfo("删除目录成功");
        }
        else return ResultInfo.failInfo("删除失败");
    }

    // 查询语句的判空交给 Controller
    @Override
    public BlogClass selectClassesByUseridAndName(int user_id, String name){
        return classMapper.selectByUseridAndName(user_id, name);
    }
}

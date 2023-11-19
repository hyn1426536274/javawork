package com.example.javawork.controller;

import com.example.javawork.pojo.ResultInfo;
import com.example.javawork.pojo.Type;
import com.example.javawork.service.TypeService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TypeController {
    @Autowired
    TypeService typeService;

    @RequestMapping("/getAllType")
    public ResultInfo getAllType(){
        List<Type> types = typeService.getAllType();
        if(!types.isEmpty()){
            return ResultInfo.successInfo("获取类别成功",types);
        }
        else return ResultInfo.failInfo("获取失败");
    }

    @PostMapping("/getTypeById")
    public ResultInfo getTypeById(HttpServletRequest request){
        int id = Integer.parseInt(request.getParameter("id"));
        if(id<=0){
            ResultInfo.failInfo("请输入正确目标id");
        }
        Type type = typeService.selectById(id);
        if(type!=null){
            return ResultInfo.successInfo("获取类别成功",type);
        }
        else return ResultInfo.failInfo("获取失败，未找到");
    }
}

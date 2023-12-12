package com.example.javawork.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class ResultInfo {

    /**
     * 返回状态码
     * 成功为1，失败为0
     * */
    private Integer status;

    /**
     * 返回的数据结果
     * */
    private Object data;

    /**
     * 描述信息
     * */
    private String message;

    /**
     * 之后该方法的重载都是调用该方法，无需自己创建对象
     * */
    public static ResultInfo successInfo(String message,Object data){return new ResultInfo(1,data,message);}

    public static ResultInfo failInfo(String message, Object data){return new ResultInfo(0,data,message);}

    public static ResultInfo successInfo(){return successInfo(null,null);}

    public static ResultInfo successInfo(String message){return successInfo(message,null);}

    public static ResultInfo failInfo(){return failInfo(null,null);}

    public static ResultInfo failInfo(String message){return failInfo(message,null);}
}

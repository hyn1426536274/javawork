package com.example.javawork.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor // 生成一个包含所有字段参数的构造函数。
@NoArgsConstructor // 生成一个无参数的默认构造函数。
//@Getter // 为所有字段生成 getter 方法。
//@Setter // 为所有非 final 字段生成 setter 方法。
@ToString // 生成一个包含所有字段的 toString 方法。
@Data
public class User {
    private int id;
    // 数据库查询返回User对象，User对象属性应与数据库表项对应
    private String username;
    private String password;
    private String email;
    private String description;
}

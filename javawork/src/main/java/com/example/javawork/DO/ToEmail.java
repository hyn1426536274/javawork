package com.example.javawork.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ToEmail implements Serializable {
    // 邮件接收方
    private String[] tos;

    private String subject;

    private String content;
}

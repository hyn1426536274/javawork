package com.example.javawork.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangeBlogParam implements Serializable {
    private String origin_title; // 目标标题
    private String title;
    private String content;
    private String description;

    private boolean published;

    private int type_id;

    private int class_id;
}

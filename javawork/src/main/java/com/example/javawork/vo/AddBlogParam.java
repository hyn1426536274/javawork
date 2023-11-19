package com.example.javawork.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddBlogParam implements Serializable {
    private String title;
    private String content;
    private String description;

    private boolean published;

    private int type_id;

    private int class_id;
}
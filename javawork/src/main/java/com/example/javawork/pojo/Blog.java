package com.example.javawork.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    private String title;
    private String content;
    private String description;
    private Timestamp create_time;
    private Timestamp update_time;
    private boolean published;
    private int views;
    private int comment_count;
    private int type_id;
    private int user_id;
}

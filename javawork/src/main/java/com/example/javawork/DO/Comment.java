package com.example.javawork.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private int id;
    private String content;
    private Timestamp create_time;
    private int blog_id;
    private int first_father_id;
    private int near_father_id;
    private int owner_id;
    private String username;
    private int approvals;
    private boolean is_deleted;
}

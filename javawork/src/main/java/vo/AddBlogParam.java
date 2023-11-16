package vo;

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
    private Timestamp create_time;
    private Timestamp update_time;
    private boolean published;
    private int views;
    private int comment_count;
    private int type_id;
    private int user_id;
}
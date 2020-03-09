package com.example.magjong.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Quesstion {
    private int id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer likeCount;
    private Integer commentCont;
    private String tag;
}

package com.example.magjong.dto;

import lombok.Data;

@Data
public class QuesitonDTO {
    private int id;
    private String title;
    private String description;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer likeCount;
    private String tag;
    private Integer commentCont;
    private User user;
}

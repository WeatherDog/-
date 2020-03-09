package com.example.magjong.model;

import lombok.Data;


@Data
public class Reply {
    private Integer id;
    private String description;
    private Integer rank;
    private Long createTime;
    private Integer replyUserId;
    private Integer likeCount;
    private Integer replyCount;
    private Integer questionId;
    private boolean status;
    private Integer replyParmentId;
}

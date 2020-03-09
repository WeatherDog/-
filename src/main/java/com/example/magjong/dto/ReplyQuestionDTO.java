package com.example.magjong.dto;

import com.example.magjong.model.Reply;
import lombok.Data;

@Data
public class ReplyQuestionDTO {
    private User user;
    private Reply reply;
}

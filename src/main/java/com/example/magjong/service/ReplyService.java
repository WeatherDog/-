package com.example.magjong.service;

import com.example.magjong.dto.PaginationDTO;
import com.example.magjong.dto.ReplyDTO;
import com.example.magjong.dto.ReplyQuestionDTO;

import java.util.List;

public interface ReplyService {
    public ReplyDTO pagination(Integer page, Integer questionId);
    public ReplyQuestionDTO addSecondLevelReply(Integer token, String description, Integer questionId,Integer replyParmentId);
    public List<ReplyQuestionDTO> iniSecondlevel(Integer replyParentId,Integer questionId);
}

package com.example.magjong.service;

import com.example.magjong.dto.PaginationDTO;
import com.example.magjong.dto.ReplyQuestionDTO;
import com.example.magjong.model.Quesstion;
import com.example.magjong.model.Reply;

import java.util.List;

public interface QuestionService {
    public PaginationDTO list(Integer sizi);
    public PaginationDTO myQuestion(Integer page,String token);
    public Quesstion lookArticle(Long id);
    public ReplyQuestionDTO addRpely(Integer token, String description,Integer questionId);
}

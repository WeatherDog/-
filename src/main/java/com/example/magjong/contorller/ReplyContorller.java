package com.example.magjong.contorller;

import com.alibaba.fastjson.JSONObject;
import com.example.magjong.dto.PaginationDTO;
import com.example.magjong.dto.ReplyDTO;
import com.example.magjong.dto.ReplyQuestionDTO;
import com.example.magjong.service.QuestionService;
import com.example.magjong.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReplyContorller {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private ReplyService replyService;
    @GetMapping("/reply")
    @ResponseBody
    public ReplyQuestionDTO replyQuestion(@RequestParam (value = "token",defaultValue = "12") Integer token,
                                          @RequestParam (value = "description",defaultValue = "没有") String description,
                                          @RequestParam (value = "questionId") Integer questionId,
                                          @RequestParam (value = "replyParmentId",defaultValue = "0") Integer replyParmentId){
        System.out.println("replyParmentId="+replyParmentId);
        if(replyParmentId==0){
            ReplyQuestionDTO addRpely = questionService.addRpely(token, description,questionId);
            return addRpely;
        }
        ReplyQuestionDTO addSecondLevelReply = replyService.addSecondLevelReply(token, description, questionId, replyParmentId);
        System.out.println("addSecondLevelReply=="+addSecondLevelReply);
        return addSecondLevelReply;
    }
    @GetMapping("/replyInfo")
    @ResponseBody
    public ReplyDTO replyInfo(@RequestParam (value = "page",defaultValue = "1") Integer page,
                            @RequestParam (value = "questionId",required = false) Integer questionId){
        ReplyDTO replyDTO = replyService.pagination(page, questionId);
        String reJson = JSONObject.toJSONString(replyDTO);
        System.out.println("reJson"+reJson);
        return replyDTO;
    }
    @PostMapping("/uploadSecondLevel")
    @ResponseBody
    public void uploadSecondLevel(){

    }
    @GetMapping("/iniSecondlevel")
    @ResponseBody
    public List<ReplyQuestionDTO> iniSecondlevel(@RequestParam (value = "replyParentId") Integer replyParentId,
                               @RequestParam (value = "questionId") Integer questionId){
        System.out.println("replyParentId="+replyParentId);
        System.out.println("questionId="+questionId);
        List<ReplyQuestionDTO> replyQuestionDTOS = replyService.iniSecondlevel(replyParentId, questionId);
        System.out.println("replyQuestionDTOS="+replyQuestionDTOS);
        return replyQuestionDTOS;
    }
    @GetMapping("/test")
    public String test(){
        return "test";
    }
}

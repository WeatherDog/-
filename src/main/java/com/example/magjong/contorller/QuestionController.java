package com.example.magjong.contorller;

import com.example.magjong.dto.ReplyQuestionDTO;
import com.example.magjong.dto.User;
import com.example.magjong.mapper.QuesstionMapper;
import com.example.magjong.mapper.ReplyMapper;
import com.example.magjong.mapper.UserMapper;
import com.example.magjong.model.Quesstion;
import com.example.magjong.model.Reply;
import com.example.magjong.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class QuestionController {
    @Autowired
    private QuesstionMapper quesstionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionService questionService;
    @GetMapping("/question/{id}")
    public ModelAndView myQuestion(@PathVariable(value = "id") Long id,
                           ModelAndView modelAndView){
        Quesstion article = quesstionMapper.getById(id);
        User user = userMapper.selectUser(article.getCreator().longValue());
        modelAndView.setViewName("question");
        modelAndView.addObject("article",article);
        modelAndView.addObject("user",user);
        modelAndView.addObject("tf",true);
        return modelAndView;
    }
    @GetMapping("/modify/{id}")
    public ModelAndView modifyQuestion(ModelAndView modelAndView,
                               @PathVariable (value = "id") Long id){
        Quesstion question = quesstionMapper.getById(id);
        System.out.println("question="+question);
        modelAndView.addObject("question_info",question);
        modelAndView.addObject("tf",true);
        modelAndView.setViewName("Launch");
        return modelAndView;
    }

}

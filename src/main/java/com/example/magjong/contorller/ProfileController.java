package com.example.magjong.contorller;

import com.alibaba.fastjson.JSONObject;
import com.example.magjong.dto.PaginationDTO;
import com.example.magjong.dto.User;
import com.example.magjong.mapper.QuesstionMapper;
import com.example.magjong.mapper.UserMapper;
import com.example.magjong.model.Quesstion;
import com.example.magjong.service.QuestionService;
import com.example.magjong.utlis.UserUtlis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProfileController {
    @Autowired
    private UserUtlis userUtlis;
    @Autowired
    private QuesstionMapper QuesstionMapper;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private UserMapper userMapper;
    @GetMapping("profile")
    public String profile(HttpServletRequest request){
        User user = userUtlis.loginStatus(request);
        HttpSession session = request.getSession();
        if (user!=null){
            session.setAttribute("user",user);
            return "profile";
        }
        return "profile";
    }
    @PostMapping("QuestionList")
    @ResponseBody
    public String MyQuestion(@RequestParam (value = "name",defaultValue = "Myquestion") String tagName,
                           @RequestParam (value = "token") String token,
                           @RequestParam(value="page" ,required = false,defaultValue = "1") Integer page,
                           Model model){
        //判断是用户点击的选项
        //如果是Myquestion
        if(tagName.equals("Myquestion")){
            PaginationDTO list = questionService.list(page);
            String s = JSONObject.toJSONString(list);
            System.out.println("s="+s);
            return s;
        }
        return null;
    }
    @PostMapping("profileData")
    @ResponseBody
    public String turnThePage(@RequestParam(value = "token") String token,
                              @RequestParam(value = "pageSize") Integer pageSize,
                              @RequestParam(value = "attr") String attr){
        PaginationDTO list = questionService.myQuestion(pageSize,token);
        String s = JSONObject.toJSONString(list);
        System.out.println("s="+s);
        return s;
    }
    @GetMapping("/lcoal/{id}")
    public ModelAndView lookArticle(@PathVariable(value = "id") Long id,
                            ModelAndView modelAndView){
        modelAndView.setViewName("question");
        Quesstion question = questionService.lookArticle(id);
        User user = userMapper.selectUser(question.getCreator().longValue());
        modelAndView.addObject("article",question);
        modelAndView.addObject("user",user);
        return modelAndView;
    }
    public void profileData(){

    }
}


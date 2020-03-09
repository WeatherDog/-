package com.example.magjong.contorller;

import com.example.magjong.dto.PaginationDTO;
import com.example.magjong.dto.User;
import com.example.magjong.service.QuestionService;
import com.example.magjong.utlis.UserUtlis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class IndexContorller {
    @Autowired
    private UserUtlis userUtlis;
    @Autowired
    private QuestionService questionService;
    @GetMapping("/")
    public String indexC(HttpServletRequest request,
                         Model model,
                         @RequestParam(value="page" ,required = false,defaultValue = "1") Integer page){
        PaginationDTO list = questionService.list(page);
        HttpSession session = request.getSession();
        model.addAttribute("pagination",list);
        User user = userUtlis.loginStatus(request);
        if (user!=null){
                session.setAttribute("user",user);
                String s;
                return "index";
            }
            return "index";
    }
}

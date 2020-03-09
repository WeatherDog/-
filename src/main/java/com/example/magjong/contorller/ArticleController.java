package com.example.magjong.contorller;

import com.example.magjong.mapper.QuesstionMapper;
import com.example.magjong.model.Quesstion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller

public class ArticleController {
    @Autowired
    private QuesstionMapper quesstionMapper;
    @GetMapping("launch")
    public String launch(){
        return "Launch";
    }
    @PostMapping("publis")
    public String doPublis(@RequestParam(value = "title",required = false) String title,
                         @RequestParam(value = "description",required = false) String description,
                         @RequestParam(value = "tag",required = false) String tag,
                         @RequestParam(value = "id",required = false) Integer id,
                         HttpServletRequest request){
        System.out.println(id);
        if(id!=null){
            Quesstion quesstion = new Quesstion();
            quesstion.setTitle(title);
            quesstion.setDescription(description);
            quesstion.setTag(tag);
            quesstion.setId(id);
            quesstionMapper.update(quesstion);
            return "redirect:/profile";
        }
        Quesstion quesstion = new Quesstion();
        System.out.println("title="+title);
        quesstion.setTitle(title);
        quesstion.setDescription(description);
        quesstion.setTag(tag);
        quesstion.setCommentCont(0);
        quesstion.setLikeCount(0);
        quesstion.setViewCount(0);
        Cookie[] cookies = request.getCookies();
        for(Cookie coo : cookies){
            if(coo.getName().equals("token")){
                quesstion.setCreator(Integer.parseInt(coo.getValue()));
                System.out.println("token="+Integer.parseInt(coo.getValue()));
            }
        }
        quesstion.setGmtCreate(System.currentTimeMillis());
        quesstion.setGmtModified(System.currentTimeMillis());
        quesstionMapper.createQuesstion(quesstion);
        return "redirect:/";
    }
}

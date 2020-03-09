package com.example.magjong.contorller;

import com.example.magjong.dto.AccessTokenDTO;
import com.example.magjong.dto.User;
import com.example.magjong.mapper.UserMapper;
import com.example.magjong.provider.GithubProvider;
import com.example.magjong.utlis.UserUtlis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class AccessCheck {
    @Autowired
    private GithubProvider provider;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserUtlis userUtlis;
    @GetMapping("callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request,
                           HttpServletResponse response){
        User user = null;
        AccessTokenDTO dto = new AccessTokenDTO();
        dto.setClient_id("d74b790bf9305aefd2ec");
        dto.setClient_secret("3c18d27b2400fa79b90acb57606e55c7df3a1dfb");
        dto.setCode(code);
        dto.setState(state);
        try {
            String accessToken = provider.getAccessToken(dto);
            user = provider.tokenGetGithubUserInfo(accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession();
        if(user!=null){
            Integer result = userMapper.judgeUser(user.getId());
            if(result==0){
                    response.addCookie(new Cookie("token",user.getId()+""));
                    System.out.println(user);
                    System.out.println(user.getAvatarUrl());
                    userMapper.createUser(user);
                    session.setAttribute("user",user);
                    return "redirect:/";
            }else {
                response.addCookie(new Cookie("token",user.getId()+""));
                session.setAttribute("user",user);
                return "redirect:/";
            }
        }
        return "redirect:/";
    }
}

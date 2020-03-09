package com.example.magjong.utlis;

import com.example.magjong.dto.User;
import com.example.magjong.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
@Component
public class UserUtlis {
    @Autowired
    private UserMapper userMapper;
    public User loginStatus(HttpServletRequest request){
        try {
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie:cookies
            ) {
                if(cookie.getName().equals("token")){
                    User user = userMapper.selectUser(Long.parseLong(cookie.getValue()));
                    return user;
                }
            }
        }catch (Exception e){
            return null;
        }
        return null;
    }
    public Integer getToken(HttpServletRequest request){
        for (Cookie cookie : request.getCookies()) {
            if(cookie.getName().equals("token")){
                return cookie.getVersion();
            }
        }
        return null;
    }
}

package com.example.magjong.contorller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Author 雷铭涛
 * Date:2020-03-06
 * vsersion 1.0
 */
@Controller
@RequestMapping("test")
public class ShiroController {
    @GetMapping("add")
    public String addUser(){
        return "error/add";
    }
    @GetMapping("up")
    public String upUser(){
        return "error/update";
    }
    @GetMapping("userIndex")
    public String index(){
        return "error/userIndex";
    }
    @GetMapping("toLogin")
    public String toLogin(){
        return "error/login";
    }
    @GetMapping("login")
    public String login(@RequestParam("name")String username,
                      @RequestParam("passwd")String password){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
            return "redirect:/test/userIndex";
        } catch (AuthenticationException e) {
            System.out.println("用户名或密码输入错误");
        }
                return "/error/login";
                }
    @RequestMapping("/hintAuthority")
    public String hintAuthoritiy(){
        return "error/Hint";
    }
}

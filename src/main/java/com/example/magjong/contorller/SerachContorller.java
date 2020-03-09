package com.example.magjong.contorller;

import com.alibaba.fastjson.JSONObject;
import com.example.magjong.dto.PaginationDTO;
import com.example.magjong.service.SerachService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.subject.Subject;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

/**
 * Author 雷铭涛
 * Date:2020-03-04
 * vsersion 1.0
 */
@Controller
public class SerachContorller {
    @Autowired
    private SerachService serachService;
    @GetMapping("/serach")
    public String serach(@RequestParam(name = "serachKey",defaultValue = "*") String key,
                                @RequestParam(name="page",defaultValue = "1") Integer page,
                                Model model){
        System.out.println(page);
        try {
            PaginationDTO serachInfo = serachService.serach(key, page);
            String jsonString = JSONObject.toJSONString(serachInfo);
            model.addAttribute("serachInfo",serachInfo);
            model.addAttribute("serachKey",key);
            return "serach";
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return null;
    }
    @GetMapping("serachPagination")
    @ResponseBody
    public PaginationDTO serachPagination(@RequestParam(name = "serachKey",defaultValue = "*") String key,
                         @RequestParam(name="page",defaultValue = "1") Integer page,
                         Model model){
        System.out.println(page);
        try {
            PaginationDTO serachInfo = serachService.serach(key, page);
            model.addAttribute("serachKey",key);
            return serachInfo;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SolrServerException e) {
            e.printStackTrace();
        }
        return null;
    }
}

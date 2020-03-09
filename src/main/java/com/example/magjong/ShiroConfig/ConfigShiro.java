package com.example.magjong.ShiroConfig;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Author 雷铭涛
 * Date:2020-03-08
 * vsersion 1.0
 */
@Configuration
public class ConfigShiro {
    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean getShiroFilterFactorBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager webSecurityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setLoginUrl("/test/toLogin");
        factoryBean.setSecurityManager(webSecurityManager);
        Map<String,String> filter = new LinkedHashMap<>();
        filter.put("/test/add","authc");
        filter.put("/test/add","perms[add]");
        factoryBean.setFilterChainDefinitionMap(filter);
        factoryBean.setUnauthorizedUrl("/test/hintAuthority");
        return factoryBean;
    }
    @Bean("defaultWebSecurityManager")
    public DefaultWebSecurityManager doDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();
        webSecurityManager.setRealm(userRealm);
        return webSecurityManager;
    }
    @Bean("userRealm")
    public UserRealm getRealm(){
        return new UserRealm();
    }
}

package com.example.magjong.ShiroConfig;

import com.example.magjong.dto.AdminDTO;
import com.example.magjong.mapper.AdminMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Author 雷铭涛
 * Date:2020-03-08
 * vsersion 1.0
 */
public class UserRealm extends AuthorizingRealm {
    @Autowired
    private AdminMapper adminMapper;
    //授权验证
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
    //登录验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        AdminDTO verificationUser = null;
        System.out.println("password===="+String.valueOf(userToken.getPassword()));
        try {
            verificationUser = adminMapper.verification(userToken.getUsername(), String.valueOf(userToken.getPassword()));
            if (userToken.equals(verificationUser.getUsername())){
                return null;
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
        return new SimpleAuthenticationInfo("",verificationUser.getPassword(),"");
    }
}

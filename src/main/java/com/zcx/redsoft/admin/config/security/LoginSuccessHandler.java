package com.zcx.redsoft.admin.config.security;

import com.alibaba.fastjson.JSON;
import com.zcx.redsoft.admin.Utils.JwtUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * 登录失败
 *
 * @author zcx
 * @version 创建时间：2019/1/2  17:15
 */
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IndexOutOfBoundsException, ServletException, IOException {
        //生成token，并把token加密相关信息缓存，具体请看实现类
        JwtUtils.createJwt(request,response,authentication);
    }

}
package com.zcx.redsoft.admin.Utils;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * jwt工具类
 *
 * @author zcx
 * @version 创建时间：2019/1/4  16:10
 */
@Component
public class JwtUtils {
    private static String tokenHead;//= "Authorization";
    private static String tokenKey;// = "123456";
    private static String tokenPrefix;// = "zcx";
    private static long expirationtime;// = 1000 * 60 * 60 * 24;

    @Value("${token.tokenHead}")
    public void setTokenHead(String tokenHead) {
        JwtUtils.tokenHead = tokenHead;
    }

    @Value("${token.tokenKey}")
    public void setTokenKey(String tokenKey) {
        JwtUtils.tokenKey = tokenKey;
    }

    @Value("${token.tokenPrefix}")
    public void setTokenPrefix(String tokenPrefix) {
        JwtUtils.tokenPrefix = tokenPrefix;
    }

    @Value("${token.expirationtime}")
    public void setExpirationtime(long expirationtime) {
        JwtUtils.expirationtime = expirationtime;
    }

    public static void createJwt(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        //获取当前用户信息
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        StringBuilder authorities = new StringBuilder();
        //stream()流操作集合  拼装用户权限
        authentication.getAuthorities().stream().forEach(g -> {
            authorities.append("," + g.getAuthority());
        });
//        for (GrantedAuthority g : authentication.getAuthorities()) {
//            authorities.append("," + g.getAuthority());
//        }
        String jwt = Jwts.builder().claim("authorities", authorities.substring(1))// 保存权限（角色）
                .setSubject(userDetails.getUsername())// 用户名写入标题
                .claim("userInfo", String.valueOf(authentication.getDetails()))
                .claim("username", userDetails.getUsername())//保存用户名
                .setExpiration(new Date(System.currentTimeMillis() + expirationtime))//设置有效时间
                .signWith(SignatureAlgorithm.HS512, tokenKey)// 签名设置
                .compact();
        response.setHeader("Content-Type", "application/json;charset=utf-8");
        response.getWriter().write(JSON.toJSONString(tokenPrefix + jwt));
        response.getWriter().flush();
    }

    public static UsernamePasswordAuthenticationToken checkJwt(HttpServletRequest request, HttpServletResponse response) {
        //获取token
        String authHeader = request.getHeader(tokenHead);
        //判断是否合法
        if (authHeader != null && authHeader.startsWith(tokenPrefix)) {
            String authToken = authHeader.substring(tokenPrefix.length());
            //解析token为claims
            Claims claims = Jwts.parser().setSigningKey(tokenKey).parseClaimsJws(authToken).getBody();
            //用户名 权限
            String user = claims.getSubject();
            List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList((String) claims.get("authorities"));
            System.out.println((String) claims.get("authorities"));
            //生成验证用户
            UsernamePasswordAuthenticationToken uToken = new UsernamePasswordAuthenticationToken(user, null, authorities);
            return uToken;
        }
        return null;
    }
}

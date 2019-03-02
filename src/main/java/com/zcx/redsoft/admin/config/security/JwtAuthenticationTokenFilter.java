package com.zcx.redsoft.admin.config.security;

import com.zcx.redsoft.admin.Utils.JwtUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token验证
 *
 * @author zcx
 * @version 创建时间：2019/1/4  11:41
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String url = request.getHeader("url");//todo 获取url
        UsernamePasswordAuthenticationToken uToken = null;
        //路径含有 noAuth 当前用户设为 noAuth 否则解析token
        if (url != null && url.contains("noAuth")) {
            uToken = new UsernamePasswordAuthenticationToken("noAuth", null);
        } else {
            //验解析token得到用户信息
            uToken = JwtUtils.checkJwt(request, response);
        }
        //设置为登录的用户
        SecurityContextHolder.getContext().setAuthentication(uToken);
        //传入下一个拦截器
        filterChain.doFilter(request, response);
    }
}

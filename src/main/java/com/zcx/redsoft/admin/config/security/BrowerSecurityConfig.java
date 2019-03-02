package com.zcx.redsoft.admin.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

/**
 * Security配置类
 *
 * @author zcx
 * @version 创建时间：2019/1/4  16:10
 */
@Configuration
public class BrowerSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    private MyAccessDecisionVoter myAccessDecisionVoter;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**");
        web.ignoring().antMatchers("/images/**");
        web.ignoring().antMatchers("/js/**");
//忽略登录界面
        web.ignoring().antMatchers("/**/noAuth/**");

        //注册地址不拦截
//        web.ignoring().antMatchers("/reg");
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin()                    //  定义当需要用户登录时候，转到的登录页面。
////                //.loginPage()           // 设置登录页面
////                //.loginProcessingUrl()  // 自定义的登录接口
////                .successHandler(new LoginSuccessHandler())
////                .failureHandler(new LoginFailureHandler())
////                .and()
////                .authorizeRequests()        // 定义哪些URL需要被保护、哪些不需要被保护
////                .antMatchers("/login.html", "/**/noAuth/**").permitAll()     // 设置所有人都可以访问登录页面
////                .anyRequest()               // 任何请求,登录后可以访问
////                .authenticated()
////                .and()
////                .csrf().disable()           // 关闭csrf防护
////                .sessionManagement().disable();

        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 使用 JWT，关闭token
                .and()
                .httpBasic();
        // 未经过认证的用户访问受保护的资源
        //.authenticationEntryPoint(new GoAuthenticationEntryPoint())


        //权限设置
        http.authorizeRequests()        // 定义哪些URL需要被保护、哪些不需要被保护
                .antMatchers("/login", "/**/noAuth/**").permitAll()
                .anyRequest().authenticated();
                //.accessDecisionManager(accessDecisionManager()); //自定义验证

//                    // 设置所有人都可以访问登录页面
//                以 "/admin/" 开头的URL只能由拥有 "ROLE_ADMIN"角色的用户访问。请注意我们使用 hasRole 方法
//                .antMatchers("/admin").hasAuthority("ADMIN")
//                .antMatchers("/user").hasAuthority("USER")
//                //hasRole 若不是以 ROLE_ 开头  在自动加  ROLE_前缀
//                .antMatchers("/uuu").access("hasRole('USER') or hasRole('ADMIN') ")


        //跳转控制
        http.formLogin()
                //.loginPage("/login")
                //.loginProcessingUrl("/login").defaultSuccessUrl("/index", true).failureUrl("/login?error")
                .successHandler(new LoginSuccessHandler())
                .failureHandler(new LoginFailureHandler())
                // 认证失败
                //.failureHandler(new GoAuthenticationFailureHandler())
                .permitAll()

                .and()
                .logout()
                //.logoutSuccessHandler(new GoLogoutSuccessHandler())
                .permitAll();
        // 记住我
        http.rememberMe().rememberMeParameter("remember-me")
                .userDetailsService(myUserDetailsService).tokenValiditySeconds(300);


        //token验证
        http.exceptionHandling()
                // 已经认证的用户访问自己没有权限的资源处理
                //.accessDeniedHandler(new GoAccessDeniedHandler())
                .and().addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

    }

    private AccessDecisionManager accessDecisionManager() {
        List<AccessDecisionVoter<?>> voters = new ArrayList<>();
        voters.add(myAccessDecisionVoter);
        return new UnanimousBased(voters);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
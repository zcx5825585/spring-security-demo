package com.zcx.redsoft.admin.controller;

import com.alibaba.fastjson.JSON;
import com.zcx.redsoft.admin.anno.PrintMethodName;
import com.zcx.redsoft.admin.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 类说明
 *
 * @author zcx
 * @version 创建时间：2018/12/27  15:14
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private RedisTemplate redisTemplate;

    @PrintMethodName("printMethodName")
    @GetMapping
    public String getUsers(HttpServletRequest request) {
        return JSON.toJSONString(accountService.getActiveAccount());
        //return "Hello Spring Security";
    }

    @GetMapping("/noAuth/login")
    public ModelAndView noAuth() {
        return new ModelAndView("/login.html");
    }

    @GetMapping("/login/noAuth")
    public String noAuth2() {
        redisTemplate.multi();

        redisTemplate.opsForValue().set("mykey", "zcx");
        String result = (String) redisTemplate.opsForValue().get("mykey");
        redisTemplate.exec();
        System.out.println(result);
        return "noAuth2";
    }
}

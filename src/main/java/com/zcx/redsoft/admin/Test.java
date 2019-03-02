package com.zcx.redsoft.admin;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * 类说明
 *
 * @author zcx
 * @version 创建时间：2019/1/4  13:53
 */
public class Test {
    public static void main(String[] args) {
//        String jwt = Jwts.builder().claim("authorities", "admin")// 保存权限（角色）
//                .setSubject("user")// 用户名写入标题
//                .claim("username", "user")
//                .setExpiration(new Date(System.currentTimeMillis() + 100000))
//                .signWith(SignatureAlgorithm.HS512, "123456")// 签名设置
//                .compact();
//        System.out.println(jwt);
        Claims claims = Jwts.parser().setSigningKey("123456").parseClaimsJws("eyJhbGciOiJIUzUxMiJ9.eyJhdXRob3JpdGllcyI6ImFkbWluIiwic3ViIjoidXNlciIsInVzZXJuYW1lIjoidXNlciIsImV4cCI6MTU0NjU4MjgwNn0.ULmQQDADx2l6JW78qilL-VX2C-u0L-LCtzZX8QG5CuaYW450F893E6q9AdhVOSFh_lbDz9Ts2p8nLia0iZ2-pQ")
                .getBody();
        System.out.println(claims.get("username"));
    }
}

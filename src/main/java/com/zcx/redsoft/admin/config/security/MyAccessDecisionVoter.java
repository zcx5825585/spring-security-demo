package com.zcx.redsoft.admin.config.security;

import com.zcx.redsoft.admin.dao.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义权限控制
 *
 * @author zcx
 * @version 创建时间：2019/1/7  10:07
 */
@Lazy
@Component
public class MyAccessDecisionVoter implements AccessDecisionVoter {
    @Autowired
    private MenuRepository menuRepository;

    //权限总表
    public static Map<String, String> allRoleWithMenu = new HashMap<>();

    @Override
    //???todo  是否生效
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    //???todo
    public boolean supports(Class clazz) {
        return true;
    }

    @Override
    //验证
    public int vote(Authentication authentication, Object object, Collection collection) {
        //获取本次请求的URL
        FilterInvocation fi = (FilterInvocation) object;
        String url = fi.getRequestUrl();
        String method = fi.getRequest().getMethod();
        if (HttpMethod.OPTIONS.equals(method)) {
            return ACCESS_GRANTED;
        }
        if (url.endsWith("/") || url.endsWith("/"))
            url = url.substring(0, url.length() - 1);
        //懒加载权限总表
        if (allRoleWithMenu.isEmpty()) {
            initAllRoleWithMenu();
        }
        //查看是总表中否有本次请求的权限
        String requestAuthentication=allRoleWithMenu.get(url + "::" + method);
        if (requestAuthentication==null)
            return ACCESS_ABSTAIN;
        //遍历当前用户角色 与总表比对进行验证
        for (GrantedAuthority g : authentication.getAuthorities()) {
            String userRoleName = g.getAuthority();
            if (requestAuthentication.contains("|" + userRoleName + "|")) {
                return ACCESS_GRANTED;
            }
        }
        return ACCESS_ABSTAIN;
    }

    //读取权限总表
    private void initAllRoleWithMenu() {
        //从数据库读取  List<Map<url,role_name>>
        List<Map<String, String>> list = menuRepository.getAllUrlWithRoleName();
        list.stream().forEach(map -> {
            String url = map.get("url");
            String method = map.get("method");
            String value = map.get("role_name");
            //merga
            //若key不存在 添加一条
            //若key已存在 以传入函数操作新旧数据作为新的value
            allRoleWithMenu.merge(url + "::" + method, "|" + value + "|", (oldValue, newValue) -> {
                return oldValue + newValue.substring(1);
            });
        });
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public static void clearAllRoleWithMenu() {
        allRoleWithMenu.clear();
    }
}
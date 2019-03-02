package com.zcx.redsoft.admin.config.security;

import com.zcx.redsoft.admin.dao.AccountRepository;
import com.zcx.redsoft.admin.dao.RoleRepository;
import com.zcx.redsoft.admin.entity.Account;
import com.zcx.redsoft.admin.entity.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 登录处理
 *
 * @author zcx
 * @version 创建时间：2019/1/4  16:10
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;

    //缓存用户信息
    private Map<String, UserDetails> userMap = new HashMap<>();

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("用户的用户名: {}", username);
        UserDetails user = userMap.get(username);
        if (user == null) {
            user = getUserFromDatabase(username);
        }
        return new User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }

    private UserDetails getUserFromDatabase(String username) throws UsernameNotFoundException {
        Account account = accountRepository.getByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException("用户名错误");
        }
        String password = account.getPassword();

        List<Role> roles = roleRepository.getByUserId(account.getId());

//        String encode = passwordEncoder.encode(password);
//        logger.info("password: {}", encode);

        List<GrantedAuthority> authsList = new ArrayList<>();
        for (Role role : roles) {
            authsList.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        // 封装用户信息，并返回。参数分别是：用户名，密码，用户权限
        UserDetails user = new User(username, password, authsList);
        //加入缓存
        userMap.put(username, user);
        return user;
    }
}
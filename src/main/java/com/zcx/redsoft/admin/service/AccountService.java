package com.zcx.redsoft.admin.service;

import com.zcx.redsoft.admin.dao.AccountRepository;
import com.zcx.redsoft.admin.dao.RoleRepository;
import com.zcx.redsoft.admin.entity.Account;
import com.zcx.redsoft.admin.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类说明
 *
 * @author zcx
 * @version 创建时间：2019/2/28  15:22
 */
@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Cacheable("accounts")
    public Account getAccountByName(String name) {
        Account account = accountRepository.getByUsername(name);
        if (account == null) {
            throw new UsernameNotFoundException("token错误");
        }
        account.setPassword(null);
        List<Role> roles = roleRepository.getByUserId(account.getId());
        account.setRoles(roles);
        return account;
    }

    public Account getActiveAccount() {
        return getAccountByName((String) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}

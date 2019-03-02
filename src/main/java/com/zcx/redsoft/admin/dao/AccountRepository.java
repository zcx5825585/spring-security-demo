package com.zcx.redsoft.admin.dao;

import com.zcx.redsoft.admin.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 类说明
 *
 * @author zcx
 * @version 创建时间：2018/12/27  17:31
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query("from Account a where a.username=?1")
    public Account getByUsername(String username);
}

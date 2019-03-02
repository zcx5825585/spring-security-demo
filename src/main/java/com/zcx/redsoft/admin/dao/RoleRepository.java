package com.zcx.redsoft.admin.dao;

import com.zcx.redsoft.admin.entity.Account;
import com.zcx.redsoft.admin.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 类说明
 *
 * @author zcx
 * @version 创建时间：2018/12/27  17:31
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(nativeQuery = true,value = "select r.* from role r left join account_role ar on r.id=ar.role_id where ar.account_id=?1")
    public List<Role> getByUserId(Integer userId);
}

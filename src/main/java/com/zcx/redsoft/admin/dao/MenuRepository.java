package com.zcx.redsoft.admin.dao;

import com.zcx.redsoft.admin.entity.Account;
import com.zcx.redsoft.admin.entity.Menu;
import com.zcx.redsoft.admin.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 类说明
 *
 * @author zcx
 * @version 创建时间：2018/12/27  17:31
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {
    @Query(nativeQuery = true,value = "select m.url,m.method,r.role_name from menu m left join menu_role mr on m.id=mr.menu_id left join role r on r.id=mr.role_id")
    public List<Map<String,String>> getAllUrlWithRoleName();
}

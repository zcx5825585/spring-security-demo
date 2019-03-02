package com.zcx.redsoft.admin.dao;

import com.zcx.redsoft.admin.entity.Account;
import com.zcx.redsoft.admin.entity.Book;
import com.zcx.redsoft.admin.entity.Menu;
import com.zcx.redsoft.admin.entity.MenuTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
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
public interface BookRepository extends JpaRepository<MenuTest, Integer> {
    @Query(nativeQuery = true,value = "SELECT * FROM sys_menu WHERE parent_id in (?1)")
    public List<MenuTest> test(List<String> ids);
}

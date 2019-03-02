package com.zcx.redsoft.admin.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * 类说明
 *
 * @author zcx
 * @version 创建时间：2018/12/27  15:32
 */
@Entity
@Table(name = "account")
@NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;

    @Transient
    private List<Role> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}

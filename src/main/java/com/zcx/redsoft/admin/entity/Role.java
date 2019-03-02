package com.zcx.redsoft.admin.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 类说明
 *
 * @author zcx
 * @version 创建时间：2019/1/4  16:22
 */
@Entity
@Table(name = "role")
@NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r")
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String roleName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}

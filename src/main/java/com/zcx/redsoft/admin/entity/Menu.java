package com.zcx.redsoft.admin.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 类说明
 *
 * @author zcx
 * @version 创建时间：2019/1/4  16:24
 */
@Entity
@Table(name = "menu")
@NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m")
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String menuName;
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}

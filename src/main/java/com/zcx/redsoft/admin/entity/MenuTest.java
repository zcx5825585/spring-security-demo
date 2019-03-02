package com.zcx.redsoft.admin.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 类说明
 *
 * @author zcx
 * @version 创建时间：2019/1/10  9:09
 */
@Entity
@Table(name="sys_menu")
@NamedQuery(name="MenuTest.findAll", query="SELECT m FROM MenuTest m")
public class MenuTest implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "uuid") @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column(name="curr_level")
    private int currLevel;

    private int display;

    @Column(name="is_active")
    private int isActive;

    @Column(name="menu_ico")
    private String menuIco;

    @Column(name="menu_name")
    private String menuName;

    @Column(name="parent_id")
    private String parentId;

    private String properties;

    private String url;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCurrLevel() {
        return this.currLevel;
    }

    public void setCurrLevel(int currLevel) {
        this.currLevel = currLevel;
    }

    public int getDisplay() {
        return this.display;
    }

    public void setDisplay(int display) {
        this.display = display;
    }

    public int getIsActive() {
        return this.isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public String getMenuIco() {
        return this.menuIco;
    }

    public void setMenuIco(String menuIco) {
        this.menuIco = menuIco;
    }

    public String getMenuName() {
        return this.menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getParentId() {
        return this.parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getProperties() {
        return this.properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
package com.zcx.redsoft.admin.entity;

import javax.persistence.*;

/**
 * 类说明
 *
 * @author zcx
 * @version 创建时间：2019/1/9  13:43
 */
@Entity
@Table(name = "book")
@NamedQuery(name = "Book.findAll", query = "SELECT b FROM Book b")
public class Book {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

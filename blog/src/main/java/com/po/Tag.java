package com.po;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 标签实体
 * @Author: 吴宸煊
 * @Date: Created in  2017/12/25
 */
@Entity
@Table(name = "t_tag")
public class Tag {

    @Id
    @GeneratedValue
    private Long id;//编号
    private String name;//名字

    @ManyToMany(mappedBy = "tags")
    private List<Blog> blogs = new ArrayList<>(); //与博客表的关系

    public Tag() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public void setBlogs(List<Blog> blogs) {
        this.blogs = blogs;
    }

    @Override
    public String toString() {
        return "Tag{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

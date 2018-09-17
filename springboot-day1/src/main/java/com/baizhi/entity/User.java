package com.baizhi.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/8/22 0022.
 */
public class User implements Serializable{
    private Integer id;
    private String  name;
    private String  password;
    private String  sex;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public User(Integer id, String name, String password, String sex) {

        this.id = id;
        this.name = name;
        this.password = password;
        this.sex = sex;
    }

    public User() {

    }
}

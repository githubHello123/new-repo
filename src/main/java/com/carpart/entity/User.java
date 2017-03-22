package com.carpart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Administrator on 2017/3/21.
 */
@Entity
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "password",nullable = false)
    private String password;
    @Column(name = "real_name",nullable = true)
    private String realName;
    @Column(name = "user_name",nullable = false)
    private String userName;
    @Column(name = "status",nullable = false,columnDefinition = "INT default 1",length = 1)
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", userName='" + userName + '\'' +
                ", status=" + status +
                '}';
    }
}

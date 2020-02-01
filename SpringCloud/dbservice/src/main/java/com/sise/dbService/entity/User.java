package com.sise.dbService.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @program: SpringCloud
 * @description: 综合实验
 * @author: wxy
 * @create: 2020-01-31 11:49
 **/

@Entity
@Table(name = "user")
public class User implements Serializable {

    private String uid;
    private String username;
    private String password;

    public User(){
        super();
    }

    public User(String uid, String username, String password){
        super();
        this.uid = uid;
        this.username = username;
        this.password = password;
    }

    @Id
    @GenericGenerator(name = "session_info_uuid_gen", strategy = "assigned")
    @GeneratedValue(generator = "session_info_uuid_gen")
    @Column(name = "uid")
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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
}

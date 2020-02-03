package com.sise.familyEducation.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: FamilyEducation
 * @description: 角色类
 * @author: wxy
 * @create: 2020-02-01 17:01
 **/

@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String role;

    @OneToMany(targetEntity = User.class,mappedBy = "role")
    Set<User> User = new HashSet<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<com.sise.familyEducation.entity.User> getUser() {
        return User;
    }

    public void setUser(Set<com.sise.familyEducation.entity.User> user) {
        User = user;
    }
}

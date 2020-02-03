package com.sise.familyEducation.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @program: FamilyEducation
 * @description: 用户类
 * @author: wxy
 * @create: 2020-02-02 12:19
 **/

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String name;
    private String username;
    private String password;
    private String phone;
    private String address;
    private String area;
    private String qualification;

    @ManyToOne(targetEntity = Role.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id",referencedColumnName = "id")
    private Role role;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

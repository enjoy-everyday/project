//package com.sise.familyEducation.entity;
//
//import lombok.Data;
//
//import javax.persistence.*;
//
///**
// * @program: FamilyEducation
// * @description: 家长类
// * @author: wxy
// * @create: 2020-02-01 17:05
// **/
//
//@Data
//@Entity
//@Table(name = "parent")
//public class Parent {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private int id;
//    private String name;
//    private String username;
//    private String password;
//    private String phone;
//    private String address;
//
//    @ManyToOne(targetEntity = Role.class,cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id",referencedColumnName = "id")
//    private Role role;
//
//}

//package com.sise.familyEducation.entity;
//
//import lombok.Data;
//
//import javax.persistence.*;
//
///**
// * @program: FamilyEducation
// * @description: 学生类
// * @author: wxy
// * @create: 2020-02-01 16:55
// **/
//
//@Data
//@Entity
//@Table(name = "student")
//public class Student {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private int id;
//    private String name;
//    private String username;
//    private String password;
//    private String phone;
//    private String area;
//    private  String qualification;
//
//    @ManyToOne(targetEntity = Role.class,cascade = CascadeType.ALL)
//    @JoinColumn(name = "role_id",referencedColumnName = "id")
//    private Role role;
//
//}

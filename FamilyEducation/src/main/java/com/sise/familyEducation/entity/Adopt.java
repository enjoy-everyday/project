//package com.sise.familyEducation.entity;
//
//import lombok.Data;
//
//import javax.persistence.*;
//
///**
// * @program: FamilyEducation
// * @description: 通过，被选择
// * @author: wxy
// * @create: 2020-02-05 16:04
// **/
//
//@Data
//@Entity
//@Table(name = "adopt")
//public class Adopt {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private int id;
//    private String date;
//
//    @ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH}, optional=false)
//    @JoinColumn(name="task_id")
//    private Task task;
//
//}

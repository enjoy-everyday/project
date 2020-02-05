package com.sise.familyEducation.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @program: FamilyEducation
 * @description: 学生接受表
 * @author: wxy
 * @create: 2020-02-04 11:38
 **/

@Data
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String data;

    @ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH}, optional=false)
    @JoinColumn(name="student_id")
    private Student student;

    @ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH}, optional=false)
    @JoinColumn(name="detail_id")
    private Detail detail;

}

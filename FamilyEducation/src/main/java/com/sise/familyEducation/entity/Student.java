package com.sise.familyEducation.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @program: FamilyEducation
 * @description: 学生类
 * @author: wxy
 * @create: 2020-02-01 16:55
 **/

@Getter
@Setter
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String name;
    private String username;
    private String phone;
    private String area;
    private  String qualification;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy="student", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval = true)
    private List<Task> tasks;

    @OneToMany(mappedBy="student", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval = true)
    private List<HistoricalTask> historicalTasks;

}

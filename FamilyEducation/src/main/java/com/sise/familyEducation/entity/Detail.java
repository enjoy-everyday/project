package com.sise.familyEducation.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @program: FamilyEducation
 * @description: 订单类
 * @author: wxy
 * @create: 2020-02-03 17:57
 **/

@Data
@Entity
@Table(name = "detail")
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private char gender;
    private Date date;
    private String price;
    private String address;
    private String grade;
    private String subject;
    private String provinceAndCity;
    private String qualification;
    private String goodAtSubjects;
    private String experience;
    private String requirement;
    private String teachingTime;
    private boolean display = true;        //是否显示

    @ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH}, optional=false)
    @JoinColumn(name="parent_id")
    private Parent parent;

    @OneToMany(mappedBy="detail", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval = true)
    private List<Task> tasks;

}

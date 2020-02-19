package com.sise.familyEducation.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @program: FamilyEducation
 * @description: 学生接受表
 * @author: wxy
 * @create: 2020-02-04 11:38
 **/

@Setter
@Getter
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String date;
    private String whetherToPass = "否";       //是否通过

    @ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH}, optional=false)
    @JoinColumn(name="student_id")
    private Student student;

    @ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH}, optional=false)
    @JoinColumn(name="detail_id")
    private Detail detail;

    @ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name="historical_detail_id")
    private HistoricalDetail historicalDetail;

//    @OneToMany(mappedBy="task", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval = true)
//    private List<Adopt> adopts;

}
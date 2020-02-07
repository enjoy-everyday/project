package com.sise.familyEducation.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @program: FamilyEducation
 * @description: 历史任务
 * @author: wxy
 * @create: 2020-02-06 15:54
 **/

@Data
@Entity
@Table(name = "historical_task")
public class HistoricalTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String date;
    private String result;      //被学生取消，被家长拒绝
    private boolean display = true;        //是否显示

    @ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH}, optional=false)
    @JoinColumn(name="student_id")
    private Student student;

    @ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH}, optional=false)
    @JoinColumn(name="historical_detail_id")
    private HistoricalDetail historicalDetail;

}

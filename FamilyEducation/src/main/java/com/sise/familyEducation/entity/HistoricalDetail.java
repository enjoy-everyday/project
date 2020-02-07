package com.sise.familyEducation.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @program: FamilyEducation
 * @description: 历史发布
 * @author: wxy
 * @create: 2020-02-06 16:36
 **/

@Data
@Entity
@Table(name = "historical_detail")
public class HistoricalDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String date;
    private String result;      //成功、失败
    private String context;
    private boolean display = true;        //是否显示

    @ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH}, optional=false)
    @JoinColumn(name="parent_id")
    private Parent parent;

    @OneToMany(mappedBy="historicalDetail", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval = true)
    private List<HistoricalTask> historicalTasks;

    @OneToMany(mappedBy="historicalDetail", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval = true)
    private List<Task> tasks;

}

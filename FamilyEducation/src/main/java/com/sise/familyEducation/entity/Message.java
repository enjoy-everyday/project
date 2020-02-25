package com.sise.familyEducation.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @program: FamilyEducation
 * @description: 存放为发送消息
 * @author: wxy
 * @create: 2020-02-14 14:35
 **/

@Data
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private boolean state = false;       //false为未读，true为已读
    private String date;
    private String message;     //拒绝、取消、接受
    private boolean display = true;        //是否显示

    @ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH}, optional=false)
    @JoinColumn(name="student_id")
    private Student student;

    @ManyToOne(cascade={CascadeType.MERGE, CascadeType.REFRESH}, optional=false)
    @JoinColumn(name="parent_id")
    private Parent parent;

}

package com.sise.familyEducation.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @program: FamilyEducation
 * @description: 家长类
 * @author: wxy
 * @create: 2020-02-01 17:05
 **/

@Data
@Entity
@Table(name = "parent")
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String name;
    private String username;
    private String phone;
    private String address;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy="parent", cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval = true)
    private List<Detail> details;

}

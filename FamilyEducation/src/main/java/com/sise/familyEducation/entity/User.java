package com.sise.familyEducation.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @program: FamilyEducation
 * @description: 用户类
 * @author: wxy
 * @create: 2020-02-02 12:19
 **/

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private Date date;
    private String password;
    private String phone;

    @ManyToOne(targetEntity = Role.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id",referencedColumnName = "id")
    private Role role;

}

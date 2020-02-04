package com.sise.familyEducation.entity;

import lombok.Data;

import javax.persistence.*;
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
    private String name;
    private String username;
    private String password;
    private String phone;
    private String address;
    private String area;
    private String qualification;

    @ManyToOne(targetEntity = Role.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id",referencedColumnName = "id")
    private Role role;

    @ManyToMany(mappedBy = "users")
    private List<Detail> details;

}

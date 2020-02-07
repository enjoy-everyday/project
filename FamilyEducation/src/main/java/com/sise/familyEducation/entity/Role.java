package com.sise.familyEducation.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: FamilyEducation
 * @description: 角色类
 * @author: wxy
 * @create: 2020-02-01 17:01
 **/

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String role;

    @OneToMany(targetEntity = User.class,mappedBy = "role")
    Set<User> User = new HashSet<>();

}

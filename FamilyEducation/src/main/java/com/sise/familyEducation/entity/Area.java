package com.sise.familyEducation.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @program: FamilyEducation
 * @description: 区
 * @author: wxy
 * @create: 2020-02-09 11:54
 **/

@Getter
@Setter
@Entity
@Table(name = "area")
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_code")
    private int id;
    private String area_name;

}

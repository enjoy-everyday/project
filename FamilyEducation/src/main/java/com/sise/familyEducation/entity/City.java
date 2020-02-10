package com.sise.familyEducation.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @program: FamilyEducation
 * @description: 城市
 * @author: wxy
 * @create: 2020-02-09 11:47
 **/

@Data
@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_code")
    private int id;
    private String city_name;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "city_code")
    private List<Area> areas;

}

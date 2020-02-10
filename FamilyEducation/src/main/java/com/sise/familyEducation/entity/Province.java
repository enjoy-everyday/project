package com.sise.familyEducation.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @program: FamilyEducation
 * @description: 省份
 * @author: wxy
 * @create: 2020-02-09 11:43
 **/

@Getter
@Setter
@Entity
@Table(name = "province")
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "province_code")
    private int id;
    private String province_name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "province_code")
    private List<City> cities;

}

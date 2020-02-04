package com.sise.familyEducation.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 * @program: FamilyEducation
 * @description: 订单类
 * @author: wxy
 * @create: 2020-02-03 17:57
 **/

@Data
@Entity
@Table(name = "detail")
public class Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    private String context;

    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinTable(name = "user_details",
            joinColumns = {@JoinColumn(name = "details_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private List<User> users;

}

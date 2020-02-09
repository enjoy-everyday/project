package com.sise.familyEducation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @program: FamilyEducation
 * @description: 基础
 * @author: wxy
 * @create: 2020-02-09 11:00
 **/

public interface BasicRepository extends JpaRepository<String, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM province")
    List<String> findAllProvince();

}

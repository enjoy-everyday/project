package com.sise.familyEducation.repository;

import com.sise.familyEducation.entity.Detail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: FamilyEducation
 * @description: 订单
 * @author: wxy
 * @create: 2020-02-03 18:05
 **/

public interface ParentRepository extends JpaRepository<Detail, Integer> {
}

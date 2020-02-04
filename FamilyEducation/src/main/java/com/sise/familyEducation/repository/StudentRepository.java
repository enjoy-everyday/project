package com.sise.familyEducation.repository;

import com.sise.familyEducation.entity.Detail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: FamilyEducation
 * @description: 学生
 * @author: wxy
 * @create: 2020-02-03 21:17
 **/

public interface StudentRepository extends JpaRepository<Detail, Integer> {
}

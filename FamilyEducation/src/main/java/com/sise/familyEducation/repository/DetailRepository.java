package com.sise.familyEducation.repository;

import com.sise.familyEducation.entity.Detail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: FamilyEducation
 * @description: 发布家教内容
 * @author: wxy
 * @create: 2020-02-04 11:54
 **/

public interface DetailRepository extends JpaRepository<Detail, Integer> {
}

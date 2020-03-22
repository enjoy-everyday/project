package com.sise.familyEducation.repository;

import com.sise.familyEducation.entity.Detail;
import com.sise.familyEducation.entity.Parent;
import com.sise.familyEducation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @program: FamilyEducation
 * @description: 订单
 * @author: wxy
 * @create: 2020-02-03 18:05
 **/

public interface ParentRepository extends JpaRepository<Parent, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM parent ORDER BY score DESC limit 10;")
    List<Parent> findParentOrderByScoreLimit();

    Parent findByPhone(String phone);

    Parent findParentByUser(User user);

}

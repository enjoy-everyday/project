package com.sise.familyEducation.repository;

import com.sise.familyEducation.entity.Detail;
import com.sise.familyEducation.entity.Parent;
import com.sise.familyEducation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: FamilyEducation
 * @description: 订单
 * @author: wxy
 * @create: 2020-02-03 18:05
 **/

public interface ParentRepository extends JpaRepository<Parent, Integer> {

    Parent findByPhone(String phone);

    Parent findParentByUser(User user);

}

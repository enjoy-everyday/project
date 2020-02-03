package com.sise.familyEducation.repository;

import com.sise.familyEducation.entity.Role;
import com.sise.familyEducation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @program: FamilyEducation
 * @description: 登录
 * @author: wxy
 * @create: 2020-02-01 21:38
 **/

public interface LoginRepository extends JpaRepository<User, Integer> {

//    @Query(nativeQuery = true, value = "SELECT * FROM Role WHERE id = (SELECT role_id FROM ((SELECT phone FROM student) UNION (SELECT phone FROM patent)) AS a WHERE a.phone = phone)")
    User findByPhone(String phone);

}

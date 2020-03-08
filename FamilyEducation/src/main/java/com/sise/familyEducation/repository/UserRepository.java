package com.sise.familyEducation.repository;

import com.sise.familyEducation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: FamilyEducation
 * @description: 用户
 * @author: wxy
 * @create: 2020-03-06 14:06
 **/

public interface UserRepository extends JpaRepository<User, Integer> {
}

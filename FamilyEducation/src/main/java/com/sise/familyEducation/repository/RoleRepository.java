package com.sise.familyEducation.repository;

import com.sise.familyEducation.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: FamilyEducation
 * @description:
 * @author: wxy
 * @create: 2020-03-05 17:09
 **/

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findRoleByRole(String role);

}

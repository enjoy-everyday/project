package com.sise.familyEducation.service;

import com.sise.familyEducation.entity.Role;
import com.sise.familyEducation.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: FamilyEducation
 * @description: 角色
 * @author: wxy
 * @create: 2020-03-05 17:10
 **/

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role findRoleByRole(String role){
        return roleRepository.findRoleByRole(role);
    }

}

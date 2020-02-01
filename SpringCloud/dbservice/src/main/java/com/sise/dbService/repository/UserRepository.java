package com.sise.dbService.repository;

import com.sise.dbService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: SpringCloud
 * @description: 综合实验
 * @author: wxy
 * @create: 2020-01-31 11:58
 **/

public interface UserRepository extends JpaRepository<User, String> {}

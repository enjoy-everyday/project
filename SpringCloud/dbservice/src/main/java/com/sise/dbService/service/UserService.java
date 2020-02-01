package com.sise.dbService.service;

import com.sise.dbService.entity.User;
import com.sise.dbService.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * @program: SpringCloud
 * @description: 综合实验
 * @author: wxy
 * @create: 2020-01-31 11:59
 **/

@Service
public class UserService {

    @Resource
    private UserRepository userRepository;

    @Transactional
    public void save(User user){
        userRepository.save(user);
    }

    public User findById(String uid){
        return userRepository.findById(uid).get();
    }

}

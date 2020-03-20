package com.sise.familyEducation.service;

import com.sise.familyEducation.entity.User;
import com.sise.familyEducation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @program: FamilyEducation
 * @description: 用户
 * @author: wxy
 * @create: 2020-03-06 14:07
 **/

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User findUserById(int id){
        return userRepository.findById(id).get();
    }

    @Transactional
    public void saveUser(User user){
        userRepository.save(user);
    }

}

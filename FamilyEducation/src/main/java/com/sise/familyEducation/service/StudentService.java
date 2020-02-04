package com.sise.familyEducation.service;

import com.sise.familyEducation.entity.Detail;
import com.sise.familyEducation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: FamilyEducation
 * @description: 学生
 * @author: wxy
 * @create: 2020-02-03 21:18
 **/

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Detail> findAll(){
        return studentRepository.findAll();
    }

}

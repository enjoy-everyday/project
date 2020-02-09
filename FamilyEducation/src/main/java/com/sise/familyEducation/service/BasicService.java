package com.sise.familyEducation.service;

import com.sise.familyEducation.repository.BasicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: FamilyEducation
 * @description: 基础
 * @author: wxy
 * @create: 2020-02-09 11:06
 **/

@Service
public class BasicService {

    @Autowired
    private BasicRepository basicRepository;

    public List<String> findAllProvince(){
        return basicRepository.findAllProvince();
    }

}

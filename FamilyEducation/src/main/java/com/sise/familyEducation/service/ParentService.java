package com.sise.familyEducation.service;

import com.sise.familyEducation.entity.Detail;
import com.sise.familyEducation.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: FamilyEducation
 * @description: 订单
 * @author: wxy
 * @create: 2020-02-03 18:06
 **/

@Service
public class ParentService {

    @Autowired
    private ParentRepository parentRepository;

    public Detail save(Detail detail){
        return parentRepository.save(detail);
    }

}

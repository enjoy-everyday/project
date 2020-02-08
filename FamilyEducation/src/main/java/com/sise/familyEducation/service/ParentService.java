package com.sise.familyEducation.service;

import com.sise.familyEducation.entity.Detail;
import com.sise.familyEducation.entity.Parent;
import com.sise.familyEducation.repository.ParentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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

    public Parent findParentByPhone(String phone){
        return parentRepository.findByPhone(phone);
    }

    @Transactional
    public Parent saveParent(Parent parent){
        return parentRepository.save(parent);
    }

}

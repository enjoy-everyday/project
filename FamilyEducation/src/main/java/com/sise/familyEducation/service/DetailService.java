package com.sise.familyEducation.service;

import com.sise.familyEducation.entity.Detail;
import com.sise.familyEducation.entity.Parent;
import com.sise.familyEducation.repository.DetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * @program: FamilyEducation
 * @description: 请家教内容
 * @author: wxy
 * @create: 2020-02-04 11:56
 **/

@Service
public class DetailService {

    @Autowired
    private DetailRepository detailRepository;

    public List<Detail> findAllDetails(){
        return detailRepository.findAll();
    }

    public Detail findDetailsById(int id){
        Optional<Detail> detail = detailRepository.findById(id);
        if (detail != null && detail.isPresent()){
            return detail.get();
        }
        return null;
    }

    public List<Detail> findNoApplicationDetailsByStudentId(int id){
        return detailRepository.findNoApplicationDetailsByStudentId(id);
    }

    @Transactional
    public Detail saveDetail(Detail detail){
        return detailRepository.save(detail);
    }

}

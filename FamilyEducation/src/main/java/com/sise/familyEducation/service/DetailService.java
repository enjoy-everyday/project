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

    public List<Detail> findDetailsByParentOrderByDate(Parent parent){
        return detailRepository.findDetailsByParentOrderByDate(parent);
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

    public List<Detail> findNoApplicationDetailsByGrade(int id, String grade){
        return detailRepository.findNoApplicationDetailsByGrade(id, grade);
    }

    public List<Detail> findNoApplicationDetailsBySubject(int id, String subject){
        return detailRepository.findNoApplicationDetailsBySubject(id, subject);
    }

    public List<Detail> findNoApplicationDetailsByGradeAndSubject(int id, String grade, String subject){
        return detailRepository.findNoApplicationDetailsByGradeAndSubject(id, grade, subject);
    }

    public List<Detail> findDetailsByAddressLike(String address){
        return detailRepository.findDetailsByAddressLike(address);
    }

    @Transactional
    public Detail saveDetail(Detail detail){
        return detailRepository.save(detail);
    }

}

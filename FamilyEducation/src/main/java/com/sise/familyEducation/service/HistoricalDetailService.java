package com.sise.familyEducation.service;

import com.sise.familyEducation.entity.HistoricalDetail;
import com.sise.familyEducation.repository.HistoricalDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: FamilyEducation
 * @description: 历史发布
 * @author: wxy
 * @create: 2020-02-06 17:09
 **/

@Service
public class HistoricalDetailService {

    @Autowired
    private HistoricalDetailRepository historicalDetailRepository;

    public void saveHistoricalDetail(HistoricalDetail historicalDetail){
        historicalDetailRepository.save(historicalDetail);
    }

}

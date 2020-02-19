package com.sise.familyEducation.service;

import com.sise.familyEducation.entity.HistoricalTask;
import com.sise.familyEducation.repository.HistoricalTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @program: FamilyEducation
 * @description: 历史任务
 * @author: wxy
 * @create: 2020-02-06 17:08
 **/

@Service
public class HistoricalTaskService {

    @Autowired
    private HistoricalTaskRepository historicalTaskRepository;

    public int getTotal(){
        return historicalTaskRepository.getTotal();
    }

    @Transactional
    public void saveHistoricalTask(HistoricalTask historicalTask){
        historicalTaskRepository.save(historicalTask);
    }

}
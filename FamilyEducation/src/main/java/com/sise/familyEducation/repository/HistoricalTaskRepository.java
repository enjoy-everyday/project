package com.sise.familyEducation.repository;

import com.sise.familyEducation.entity.HistoricalTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @program: FamilyEducation
 * @description: 历史任务
 * @author: wxy
 * @create: 2020-02-06 17:08
 **/

public interface HistoricalTaskRepository extends JpaRepository<HistoricalTask, Integer> {

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM historical_task")
    int getTotal();

}

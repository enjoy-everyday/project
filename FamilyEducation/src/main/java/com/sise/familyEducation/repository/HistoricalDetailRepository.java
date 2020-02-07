package com.sise.familyEducation.repository;

import com.sise.familyEducation.entity.HistoricalDetail;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: FamilyEducation
 * @description: 历史发布
 * @author: wxy
 * @create: 2020-02-06 17:08
 **/

public interface HistoricalDetailRepository extends JpaRepository<HistoricalDetail, Integer> {
}

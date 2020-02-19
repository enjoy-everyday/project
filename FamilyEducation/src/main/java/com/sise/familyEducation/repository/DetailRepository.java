package com.sise.familyEducation.repository;

import com.sise.familyEducation.entity.Detail;
import com.sise.familyEducation.entity.Parent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @program: FamilyEducation
 * @description: 发布家教内容
 * @author: wxy
 * @create: 2020-02-04 11:54
 **/

public interface DetailRepository extends JpaRepository<Detail, Integer> {

    @Query(nativeQuery = true, value = "SELECT detail.* FROM detail LEFT JOIN (SELECT * FROM task WHERE task.student_id = (SELECT id FROM student WHERE id = ?1)) t ON detail.id = t.detail_id WHERE t.detail_id IS NULL")
    List<Detail> findNoApplicationDetailsByStudentId(int id);

}
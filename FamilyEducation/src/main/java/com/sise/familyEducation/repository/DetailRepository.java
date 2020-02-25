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

    @Query(nativeQuery = true, value = "SELECT * FROM (SELECT detail.* FROM detail LEFT JOIN (SELECT * FROM task WHERE task.student_id = (SELECT id FROM student WHERE id = ?1)) t ON detail.id = t.detail_id WHERE t.detail_id IS NULL) g WHERE g.grade = ?2")
    List<Detail> findNoApplicationDetailsByGrade(int id, String grade);

    @Query(nativeQuery = true, value = "SELECT * FROM (SELECT detail.* FROM detail LEFT JOIN (SELECT * FROM task WHERE task.student_id = (SELECT id FROM student WHERE id = ?1)) t ON detail.id = t.detail_id WHERE t.detail_id IS NULL) s WHERE s.subject = ?2")
    List<Detail> findNoApplicationDetailsBySubject(int id, String subject);

    @Query(nativeQuery = true, value = "SELECT * FROM (SELECT detail.* FROM detail LEFT JOIN (SELECT * FROM task WHERE task.student_id = (SELECT id FROM student WHERE id = ?1)) t ON detail.id = t.detail_id WHERE t.detail_id IS NULL) g WHERE g.grade = ?2 AND g.subject = ?3")
    List<Detail> findNoApplicationDetailsByGradeAndSubject(int id, String grade, String subject);

    List<Detail> findDetailsByAddressLike(String address);

}

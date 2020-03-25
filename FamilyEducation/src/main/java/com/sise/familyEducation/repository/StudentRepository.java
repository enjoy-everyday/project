package com.sise.familyEducation.repository;

import com.sise.familyEducation.entity.Detail;
import com.sise.familyEducation.entity.Student;
import com.sise.familyEducation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @program: FamilyEducation
 * @description: 学生
 * @author: wxy
 * @create: 2020-02-03 21:17
 **/

public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM student ORDER BY score DESC limit 10;")
    List<Student> findStudentOrderByScoreLimit();

    Student findByUser(User user);

    @Query(nativeQuery = true, value = "SELECT * FROM student WHERE qualification != ?1 AND score >= ?2")
    List<Student> findStudentsByQualificationNotAndScore(String qualification, float score);

    @Query(nativeQuery = true, value = "SELECT * FROM student WHERE qualification LIKE '%士%' AND score >= ?1")
    List<Student> findStudentsByQualificationLikeAndScore(float score);

    @Query(nativeQuery = true, value = "SELECT * FROM student WHERE qualification =?1 AND score >= ?2")
    List<Student> findStudentsByQualificationAndScore(String qualification, float score);

}

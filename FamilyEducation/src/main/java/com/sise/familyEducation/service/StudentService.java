package com.sise.familyEducation.service;

import com.sise.familyEducation.entity.Detail;
import com.sise.familyEducation.entity.Student;
import com.sise.familyEducation.entity.User;
import com.sise.familyEducation.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @program: FamilyEducation
 * @description: 学生
 * @author: wxy
 * @create: 2020-02-03 21:18
 **/

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> findAllStudents(){
        return studentRepository.findAll();
    }

    public Student findStudentByUser(User user){
        return studentRepository.findByUser(user);
    }

    public List<Student> findStudentOrderByScoreLimit(){
        return studentRepository.findStudentOrderByScoreLimit();
    }

    public List<Student> findStudentsByQualificationNotAndScore(String qualification, float score){
        return studentRepository.findStudentsByQualificationNotAndScore(qualification, score);
    }

    public List<Student> findStudentsByQualificationLikeAndScore(float score){
        return studentRepository.findStudentsByQualificationLikeAndScore(score);
    }

    public List<Student> findStudentsByQualificationAndScore(String qualification, float score){
        return studentRepository.findStudentsByQualificationAndScore(qualification, score);
    }

    @Transactional
    public void saveStudent(Student student){
        studentRepository.save(student);
    }

}

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

    public Iterable<Student> findAllStudentSort(Sort sort){
        return studentRepository.findAll(sort);
    }

    @Transactional
    public void saveStudent(Student student){
        studentRepository.save(student);
    }

}

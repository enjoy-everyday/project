package com.sise.familyEducation.repository;

import com.sise.familyEducation.entity.Message;
import com.sise.familyEducation.entity.Parent;
import com.sise.familyEducation.entity.Student;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: FamilyEducation
 * @description: 消息
 * @author: wxy
 * @create: 2020-02-23 17:56
 **/

public interface MessageRepository extends JpaRepository<Message, Integer> {

    List<Message> findAllByStudentAndDisplayOrderByDate(Student student, boolean display);

    List<Message> findAllByParentAndDisplayOrderByDate(Parent parent, boolean display);

    int countByStudentAndState(Student student, boolean state);

    int countByParentAndState(Parent parent, boolean state);

}

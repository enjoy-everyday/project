package com.sise.familyEducation.repository;

import com.sise.familyEducation.entity.Detail;
import com.sise.familyEducation.entity.Student;
import com.sise.familyEducation.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @program: FamilyEducation
 * @description: 任务
 * @author: wxy
 * @create: 2020-02-04 13:43
 **/

public interface TaskRepository extends JpaRepository<Task, Integer> {
}

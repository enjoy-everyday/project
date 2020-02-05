package com.sise.familyEducation.repository;

import com.sise.familyEducation.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @program: FamilyEducation
 * @description: 任务
 * @author: wxy
 * @create: 2020-02-04 13:43
 **/

public interface TaskRepository extends JpaRepository<Task, Integer> {
}

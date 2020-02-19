package com.sise.familyEducation.repository;

import com.sise.familyEducation.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @program: FamilyEducation
 * @description: 任务
 * @author: wxy
 * @create: 2020-02-04 13:43
 **/

public interface TaskRepository extends JpaRepository<Task, Integer> {

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "DELETE FROM task WHERE id = ?1")
    void deleteTaskById(int id);

}
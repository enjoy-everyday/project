package com.sise.familyEducation.service;

import com.sise.familyEducation.entity.Task;
import com.sise.familyEducation.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @program: FamilyEducation
 * @description: 任务
 * @author: wxy
 * @create: 2020-02-04 13:44
 **/

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task findTaskById(int id){
        return taskRepository.findById(id).get();
    }

    @Transactional
    public void saveTask(Task task){
        taskRepository.save(task);
    }

}

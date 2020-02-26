package com.sise.familyEducation.service;

import com.sise.familyEducation.entity.Detail;
import com.sise.familyEducation.entity.Student;
import com.sise.familyEducation.entity.Task;
import com.sise.familyEducation.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
        Optional<Task> task = taskRepository.findById(id);
        if (task != null && task.isPresent()){
            return task.get();
        }
        return null;
    }

    public List<Task> findTasksByStudentAndResult(Student student, String result){
        return taskRepository.findTasksByStudentAndResult(student, result);
    }

    public List<Task> findTasksByDetailAndResult(Detail detail, String result){
        return taskRepository.findTasksByDetailAndResult(detail, result);
    }

    @Transactional
    public void saveTask(Task task){
        taskRepository.save(task);
    }

    @Transactional
    public void deleteTaskById(int id){
        taskRepository.deleteTaskById(id);
    }

}

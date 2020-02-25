package com.sise.familyEducation.service;

import com.sise.familyEducation.entity.Message;
import com.sise.familyEducation.entity.Parent;
import com.sise.familyEducation.entity.Student;
import com.sise.familyEducation.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: FamilyEducation
 * @description: 消息
 * @author: wxy
 * @create: 2020-02-23 17:57
 **/

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> findAllByStudentAndDisplayOrderByDate(Student student, boolean display){
        return messageRepository.findAllByStudentAndDisplayOrderByDate(student, display);
    }

    public List<Message> findAllByParentAndDisplayOrderByDate(Parent parent, boolean display){
        return messageRepository.findAllByParentAndDisplayOrderByDate(parent, display);
    }

    public Message findMessageById(int id){
        return messageRepository.findById(id).get();
    }

    public int countByStudentAndState(Student student, boolean state){
        return messageRepository.countByStudentAndState(student, state);
    }

    public int countByParentAndState(Parent parent, boolean state){
        return messageRepository.countByParentAndState(parent, state);
    }

    @Transactional
    public void saveMessage(Message message){
        messageRepository.save(message);
    }

}

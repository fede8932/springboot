package com.cursojava.curso.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursojava.curso.models.Task;
import com.cursojava.curso.models.User;
import com.cursojava.curso.repository.TaskRepository;
import com.cursojava.curso.repository.UserRepository;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Task> getAllTask() throws Exception {
        try {
            return taskRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void createTask(Task task) throws Exception {
        try{
            taskRepository.save(task);
        }catch(Exception e){
            throw new Exception(e);
        }
    }

    public void updateTask(String id, String userId) throws Exception {
        try {
            Task tarea = taskRepository.findById(Long.parseLong(id)).get();
            User usuario = userRepository.findById(Long.parseLong(userId)).get();
            tarea.setUser(usuario);
            taskRepository.save(tarea);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
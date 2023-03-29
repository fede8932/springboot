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

    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    public void createTask(Task task) {
        taskRepository.save(task);
    }

    public void updateTask(String id, String userId) {
        try {
            Task tarea = taskRepository.findById(Long.parseLong(id)).get();
            User usuario = userRepository.findById(Long.parseLong(userId)).get();
            tarea.setUser(usuario);
            taskRepository.save(tarea);
        } catch (Exception e) {
            throw e;
        }
    }
}
package com.cursojava.curso.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cursojava.curso.models.Task;
import com.cursojava.curso.services.TaskService;


@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks() {
        try {
            return taskService.getAllTask();
        } catch (Exception e) {
            System.out.print(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Se produjo un error en el controlador",e);
        }
    }

    @PostMapping
    public void createTask(@RequestBody Task task) {
        try {
            taskService.createTask(task);
        } catch (Exception e) {
            System.out.print(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Se produjo un error en el controlador",e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTask(@PathVariable String id, @RequestParam String userId) {
        try {
            taskService.updateTask(id, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body("Actualizado");
        } catch (Exception e) {
            System.out.print(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Se produjo un error en el controlador",e);
        }
    }
}
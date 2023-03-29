package com.cursojava.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursojava.curso.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
package com.cursojava.curso.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursojava.curso.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
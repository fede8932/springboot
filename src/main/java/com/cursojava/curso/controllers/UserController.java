package com.cursojava.curso.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cursojava.curso.dto.CreateUserDto;
import com.cursojava.curso.dto.LoginUserDto;
import com.cursojava.curso.dto.ReturnUserDto;
import com.cursojava.curso.models.User;
import com.cursojava.curso.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        try {
            return userService.getAllUsers();
        } catch (Exception e) {
            System.out.print(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Se produjo un error en el controlador",e);
        }
    }

    @PostMapping
    public ResponseEntity<String> createUsers(@RequestBody CreateUserDto user) {
        try {
            userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("Creado con exito");
        } catch (Exception e) {
            System.out.print(e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Se produjo un error en el controlador",e);
        }
    }

    @PostMapping("/login")
    public ReturnUserDto loginUser(@RequestBody LoginUserDto dataUser) {
        try {
            return userService.loginUser(dataUser);
        } catch (Exception e) {
            System.out.print(e);
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Los datos de inicio de sesión no son válidos",e);
        }
    }

}
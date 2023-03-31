package com.cursojava.curso.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursojava.curso.dto.CreateUserDto;
import com.cursojava.curso.dto.LoginUserDto;
import com.cursojava.curso.models.User;
import com.cursojava.curso.repository.UserRepository;
import com.cursojava.curso.security.PasswordEncoder;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() throws Exception {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void createUser(CreateUserDto user) throws Exception {
        try {
            User newUser = new User();
            newUser.setNombre(user.getNombre());
            newUser.setApellido(user.getApellido());
            newUser.setEmail(user.getEmail());
            newUser.setPass(passwordEncoder.generateHash(user.getPass()));
            userRepository.save(newUser);
        } catch (Exception e) {
            System.out.print(e);
            throw new Exception(e);
        }
    }

    public String loginUser(LoginUserDto dataUser) throws Exception {
        try {
            User user = userRepository.findByEmail(dataUser.getEmail());
            if(user == null){
                throw new NullPointerException("El usuario no ha sido encontrado");
            }
            if(!passwordEncoder.validatePass(dataUser.getPass(), user.getPass())){
                throw new NullPointerException("Usuario o contraseña no válidos");
            }
            return "pass";
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}
package com.cursojava.curso.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cursojava.curso.models.User;
import com.cursojava.curso.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() throws Exception {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void createUser(User user) throws Exception {
        try{
            userRepository.save(user);
        }catch(Exception e){
            System.out.print(e);
            throw new Exception(e);
        }
    }
}
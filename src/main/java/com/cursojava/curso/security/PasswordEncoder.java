package com.cursojava.curso.security;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoder {
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
    public String generateHash(String pass){
        String hashPass = this.encoder.encode(pass);
        return hashPass;
    }
    public Boolean validatePass(String passUser, String hash){
        return encoder.matches(passUser, hash);
    }
}

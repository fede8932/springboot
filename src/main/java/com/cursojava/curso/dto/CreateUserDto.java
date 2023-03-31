package com.cursojava.curso.dto;

import java.io.Serializable;

import lombok.Getter;

public class CreateUserDto implements Serializable{
    
    @Getter
	private String nombre;

    @Getter
	private String apellido;

    @Getter
	private String email;

    @Getter
	private String pass;
}
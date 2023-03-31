package com.cursojava.curso.dto;

import java.io.Serializable;

import lombok.Getter;

public class LoginUserDto implements Serializable{

    @Getter
	private String email;

    @Getter
	private String pass;
}
package com.cursojava.curso.dto;

import lombok.Getter;
import lombok.Setter;

public class ReturnUserDto {

    @Getter
    @Setter
	private String nombre;

    @Getter
    @Setter
	private String email;

    @Getter
    @Setter
	private String token;
}
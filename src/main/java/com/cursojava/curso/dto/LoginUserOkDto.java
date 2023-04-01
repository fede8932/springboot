package com.cursojava.curso.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

public class LoginUserOkDto implements Serializable{

    @Getter
    @Setter
	private Long id;

    @Getter
    @Setter
	private String nombre;

    @Getter
    @Setter
	private String apellido;

    @Getter
    @Setter
	private String email;
}
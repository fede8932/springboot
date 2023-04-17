package com.cursojava.curso.security;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class TokenAuthorizationManager implements AuthorizationManager<HttpSecurity> {

    @Override
    public void verify(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new AccessDeniedException("Token no proporcionado");
        }
        String token = authorizationHeader.substring(7);
        if (!JwtUtils.validateToken(token, "usuario")) {
            throw new AccessDeniedException("Token inválido");
        }
        // Aquí debes implementar la lógica para determinar si el usuario actual está autorizado para acceder a la solicitud HTTP.
        // Puedes utilizar los métodos de la clase HttpSecurity para definir las reglas de autorización.
        // Por ejemplo:
        if (!request.isUserInRole("ROLE_USER")) {
            throw new AccessDeniedException("No tienes acceso a esta página");
        }
        chain.doFilter(request, response);
    }
}     

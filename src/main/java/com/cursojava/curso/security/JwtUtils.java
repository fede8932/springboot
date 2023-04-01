package com.cursojava.curso.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Component;

import com.cursojava.curso.dto.LoginUserOkDto;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {
    private static final String SECRET_KEY = "albondiga";
    private static final long EXPIRATION_TIME = 86400000; // 24 horas

    public static String generateToken(LoginUserOkDto user) {
        System.out.print(SECRET_KEY);
        Claims claims = Jwts.claims();
        claims.put("id", user.getId());
        claims.put("nombre", user.getNombre());
        claims.put("apellido", user.getApellido());
        claims.put("email", user.getEmail());

        Key signingKey = new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());

        Date now = new Date();
        Date expirationTime = new Date(now.getTime() + EXPIRATION_TIME);

        String newToken = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expirationTime)
                .signWith(SignatureAlgorithm.HS256, signingKey)
                .compact();

        return newToken;
    }

    public static boolean validateToken(String token, String username) {
        Key signingKey = new SecretKeySpec(SECRET_KEY.getBytes(), SignatureAlgorithm.HS256.getJcaName());

        Claims claims = Jwts.parser()
                .setSigningKey(signingKey)
                .parseClaimsJws(token)
                .getBody();

        String subject = claims.getSubject();

        return subject.equals(username) && !claims.getExpiration().before(new Date());
    }
}

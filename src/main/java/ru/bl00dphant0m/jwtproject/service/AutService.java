package ru.bl00dphant0m.jwtproject.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Set;

import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;

@Service
public class AutService {
    //private String jwtSecret;
    @Value("${jwt.expiration}")
    private long jwtExpiration;

    @Value("${jwt.secret}")
    private String jwtSecret;

    private SecretKey key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }


    public String generateToken(String username, Set<String> roles) {
        return Jwts.builder()
                .subject(username)
                .claim("roles", roles)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(key)
                .compact();
    }

    public Claims validateToken(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(key) // Устанавливаем ключ для проверки подписи
                    .build()
                    .parseSignedClaims(token) // Разбираем токен и проверяем подпись
                    .getPayload(); // Получаем claims (утверждения) из токена
        } catch (JwtException | IllegalArgumentException e) {
            // Ловим исключения, которые могут возникнуть при невалидном токене
            throw new RuntimeException("Invalid JWT token: " + e.getMessage());
        }
    }
}
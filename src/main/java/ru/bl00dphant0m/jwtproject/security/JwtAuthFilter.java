package ru.bl00dphant0m.jwtproject.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.bl00dphant0m.jwtproject.model.entity.User;
import ru.bl00dphant0m.jwtproject.service.AutService;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private AutService autService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String authorization = request.getHeader("Authorization");
            log.info("Authorization: " + authorization);
            if (authorization != null && authorization.startsWith("Bearer ")) {
                log.info("Authorization: " + authorization.substring(7));
                String token = authorization.substring(7);
                log.info("token: " + token);

                Claims claims = autService.validateToken(token);
                System.out.println(claims);

                String username = claims.getSubject();
                List<String> roles = (List<String>) claims.get("roles", List.class);
                log.info("roles: " +   claims.get("roles", List.class).toString());

                //log.info("id: " + id);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        new CustomUserPrincipal(username, roles),
                        null,
                        roles.stream().map(SimpleGrantedAuthority::new).toList()
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            log.info("Exception: " + e.getMessage());
        }
        filterChain.doFilter(request, response);
    }
}

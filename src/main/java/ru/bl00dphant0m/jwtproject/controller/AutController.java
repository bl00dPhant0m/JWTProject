package ru.bl00dphant0m.jwtproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bl00dphant0m.jwtproject.model.entity.User;
import ru.bl00dphant0m.jwtproject.security.CustomUserDetailsService;
import ru.bl00dphant0m.jwtproject.service.AutService;
import ru.bl00dphant0m.jwtproject.service.user.UserService;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AutController {
    private final CustomUserDetailsService customUserDetailsService;
    private final AutService autService;


    @PostMapping("/login")
    public ResponseEntity<String> getToken(@RequestParam String username){
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);

        Set<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        return ResponseEntity.ok(autService.generateToken(username, roles));

    }
}

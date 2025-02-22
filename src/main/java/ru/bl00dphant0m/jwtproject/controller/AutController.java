package ru.bl00dphant0m.jwtproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bl00dphant0m.jwtproject.model.entity.User;
import ru.bl00dphant0m.jwtproject.service.AutService;
import ru.bl00dphant0m.jwtproject.service.user.UserService;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class AutController {
    private final UserService userService;
    private final AutService autService;

    @PostMapping("/login")
    public ResponseEntity<String> get(@RequestParam String username){
        Set<String> userRoles = userService.getUserRolesByUsername(username);
        return ResponseEntity.ok(autService.generateToken(username, userRoles));

    }
}

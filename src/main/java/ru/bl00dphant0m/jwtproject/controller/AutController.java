package ru.bl00dphant0m.jwtproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
@Slf4j
public class AutController {
    private final UserService userService;
    private final AutService autService;

    @PostMapping("/login")
    public ResponseEntity<String> get(@RequestParam String username){
        User user = userService.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));

        Set<String> userRoles = userService.getUserRolesByUsername(username);
        log.info(userRoles.toString());
        return ResponseEntity.ok(autService.generateToken(username, userRoles));

    }
}

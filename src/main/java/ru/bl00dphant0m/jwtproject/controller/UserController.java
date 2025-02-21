package ru.bl00dphant0m.jwtproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bl00dphant0m.jwtproject.model.entity.User;
import ru.bl00dphant0m.jwtproject.service.user.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<User> getUser(@RequestParam String username) {

    }
}

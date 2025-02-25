package ru.bl00dphant0m.jwtproject.service.user;

import ru.bl00dphant0m.jwtproject.model.entity.User;

import java.util.Optional;
import java.util.Set;

public interface UserService {
    User save(User user);

    Optional<User> findByUsername(String username);

    User findById(Long id);

    Set<String> getUserRolesByUsername(String username);

    Optional<User> findByUsernameNoToken(String username);
}

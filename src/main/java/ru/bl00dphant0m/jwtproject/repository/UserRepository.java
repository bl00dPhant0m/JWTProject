package ru.bl00dphant0m.jwtproject.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.bl00dphant0m.jwtproject.model.entity.User;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT r FROM User u JOIN u.roles r WHERE u.username = :username")
    Set<String> getUserRolesByUsername(@Param("username") String username);

    Optional<User> findByUsername(String username);
}

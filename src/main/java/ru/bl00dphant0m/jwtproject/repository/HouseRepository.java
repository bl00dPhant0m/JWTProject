package ru.bl00dphant0m.jwtproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bl00dphant0m.jwtproject.model.entity.House;

public interface HouseRepository extends JpaRepository<House, Long> {
}

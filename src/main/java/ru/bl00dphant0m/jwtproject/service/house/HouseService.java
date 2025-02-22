package ru.bl00dphant0m.jwtproject.service.house;

import ru.bl00dphant0m.jwtproject.model.entity.House;

public interface HouseService {
    House createHouse(House house, long ownerId);
    void addResident(long houseId, long residentId);

}

package ru.bl00dphant0m.jwtproject.service.house;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bl00dphant0m.jwtproject.model.entity.House;
import ru.bl00dphant0m.jwtproject.model.entity.User;
import ru.bl00dphant0m.jwtproject.repository.HouseRepository;
import ru.bl00dphant0m.jwtproject.service.user.UserService;

@Service
@RequiredArgsConstructor
public class HouseServiceImpl implements HouseService {
    private final HouseRepository houseRepository;
    private final UserService userService;

    @Override
    public House createHouse(House house, long ownerId) {
        User owner = userService.findById(ownerId);
        house.setOwner(owner);
        return houseRepository.save(house);
    }

    @Override
    public void addResident(long houseId, long residentId) {
        House house = houseRepository.findById(houseId)
                .orElseThrow(() -> new IllegalArgumentException("House not found"));

        User user = userService.findById(residentId);

        if (house.getOwner().getId() != user.getId()) {
            throw new IllegalArgumentException("Resident id mismatch");
        }

        house.addResident(user);
        houseRepository.save(house);
    }
}

package ru.bl00dphant0m.jwtproject.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "houses")
@NoArgsConstructor
@Getter
@Setter
public class House {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String address;

    @ManyToOne()
    @JoinColumn(name = "user_id")
    private User owner;

    @ManyToMany(mappedBy = "ownedHouses", fetch = FetchType.LAZY)
    private Set<User> residents = new HashSet<>();

    public House(String address) {
        this.address = address;
    }

    public void addResident(User user) {
        residents.add(user);
        user.getOwnedHouses().add(this);
    }

    public void removeResident(User user) {
        residents.remove(user);
        user.getOwnedHouses().remove(this);
    }

    public void setOwner(User user) {
        owner = user;
        user.getOwnOfHouses().add(this);
    }


}

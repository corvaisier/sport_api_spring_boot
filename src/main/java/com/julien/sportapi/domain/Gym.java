package com.julien.sportapi.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Gym {
    @Id
    private UUID gymID;
    private String gymName;
    private String gymLocation;

    public Gym(UUID gym_ID, String gym_Name, String gym_Location) {
        this.gymID = gym_ID;
        this.gymName = gym_Name;
        this.gymLocation = gym_Location;
    }
}

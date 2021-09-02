package com.julien.sportapi.domain;

import java.util.UUID;

public class Gym {
    private UUID gymID;
    private String gymName;
    private String gymLocation;

    public Gym(UUID gym_ID, String gym_Name, String gym_Location) {
        this.gymID = gym_ID;
        this.gymName = gym_Name;
        this.gymLocation = gym_Location;
    }

    public UUID getGymID() {
        return gymID;
    }

    public String getGymName() {
        return gymName;
    }

    public void setGymName(String gymName) {
        this.gymName = gymName;
    }

    public String getGymLocation() {
        return gymLocation;
    }

    public void setGymLocation(String gymLocation) {
        this.gymLocation = gymLocation;
    }

    public String toString() {
        return "Gym{" +
                "id=" + gymID +
                ", name='" + gymName + '\'' +
                ", location='" + gymLocation + '\'' +
                '}';
    }
}

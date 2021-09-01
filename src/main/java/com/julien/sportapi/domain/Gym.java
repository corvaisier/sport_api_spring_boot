package com.julien.sportapi.domain;

import java.util.UUID;

public class Gym {
    private UUID gym_ID;
    private String gym_Name;
    private String gym_Location;

    public Gym(UUID gym_ID, String gym_Name, String gym_Location) {
        this.gym_ID = gym_ID;
        this.gym_Name = gym_Name;
        this.gym_Location = gym_Location;
    }

    public UUID getGym_ID() {
        return gym_ID;
    }

    public String getGym_Name() {
        return gym_Name;
    }

    public void setGym_Name(String gym_Name) {
        this.gym_Name = gym_Name;
    }

    public String getGym_Location() {
        return gym_Location;
    }

    public void setGym_Location(String gym_Location) {
        this.gym_Location = gym_Location;
    }

    public String toString() {
        return "Gym{" +
                "id=" + gym_ID +
                ", name='" + gym_Name + '\'' +
                ", location='" + gym_Location + '\'' +
                '}';
    }
}

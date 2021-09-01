package com.julien.sportapi.domain;

import java.util.UUID;

public class Coach {
    private UUID coach_ID;
    private String coach_Name;

    public Coach(UUID coach_ID, String coach_Name) {
        this.coach_ID = coach_ID;
        this.coach_Name = coach_Name;
    }

    public UUID getCoach_ID() {
        return coach_ID;
    }

    public String getCoach_Name() {
        return coach_Name;
    }

    public void setCoach_Name(String coach_Name) {
        this.coach_Name = coach_Name;
    }

    public String toString() {
        return "Coach{" +
                "id=" + coach_ID +
                ", name='" + coach_Name + 
                '}';
    }
    
}

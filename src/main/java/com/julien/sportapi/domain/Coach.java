package com.julien.sportapi.domain;

import java.util.UUID;

public class Coach {
    private final UUID coachID;
    private String coachName;

    public Coach(UUID coachID, String coachName) {
        this.coachID = coachID;
        this.coachName = coachName;
    }

    public UUID getCoachID() {
        return coachID;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String toString() {
        return "Coach{" +
                "id=" + coachID +
                ", name='" + coachName +
                '}';
    }
    
}

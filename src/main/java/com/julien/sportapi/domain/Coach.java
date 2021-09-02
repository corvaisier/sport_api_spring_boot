package com.julien.sportapi.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class Coach {
    @Id
    private UUID coachId;
    private String coachName;

    public Coach(UUID coachID, String coachName) {
        this.coachId = coachID;
        this.coachName = coachName;
    }
    
}

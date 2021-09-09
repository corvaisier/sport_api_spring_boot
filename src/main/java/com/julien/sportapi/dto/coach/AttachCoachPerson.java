package com.julien.sportapi.dto.coach;

import lombok.Data;

import java.util.UUID;

@Data
public class AttachCoachPerson {
    private UUID coachId;
    private UUID personId;
}

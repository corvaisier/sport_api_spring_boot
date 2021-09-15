package com.julien.sportapi.dto.coach;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
@AllArgsConstructor
@Data
public class AddPersonToCoachList {
    private UUID coachId;
    private UUID personId;
}

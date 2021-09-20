package com.julien.sportapi.dto.coach;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;
@AllArgsConstructor
@Data
public class AddPersonToCoachList {
    @NonNull
    private UUID coachId;
    @NonNull
    private UUID personId;
}

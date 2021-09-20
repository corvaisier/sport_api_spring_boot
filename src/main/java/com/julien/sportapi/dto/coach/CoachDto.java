package com.julien.sportapi.dto.coach;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@AllArgsConstructor
@Data
public class CoachDto {
    @NonNull
    private String name;
    @NonNull
    private String password;
}

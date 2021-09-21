package com.julien.sportapi.dto.coach;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Email;

@AllArgsConstructor
@Data
public class CoachDto {
    @NonNull
    private String name;
    @NonNull
    @Email
    private String email;
    @NonNull
    private String password;
}

package com.julien.sportapi.dto.coach;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Email;

@AllArgsConstructor
@Data
public class CoachDtoForUpdate {
    @NonNull
    private String currentName;
    @NonNull
    private String newName;
    @NonNull
    @Email
    private String currentEmail;
    @NonNull
    @Email
    private String newEmail;
    @NonNull
    private String currentPassword;
    @NonNull
    private String newPassword;
}

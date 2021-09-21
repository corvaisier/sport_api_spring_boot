package com.julien.sportapi.dto.person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@AllArgsConstructor
@Data
public class PersonDtoForUpdate {
    @NonNull
    private String name;
    @NonNull
    private String firstName;
    @NonNull
    private String currentEmail;
    @NonNull
    private String newEmail;
    @NonNull
    private String currentPassword;
    @NonNull
    private String newPassword;
}


package com.julien.sportapi.dto.coach;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@AllArgsConstructor
@Data
public class CoachDtoForUpdate {
    @NonNull
    private String currentName;
    @NonNull
    private String newName;
    @NonNull
    private String currentPassword;
    @NonNull
    private String newPassword;
}

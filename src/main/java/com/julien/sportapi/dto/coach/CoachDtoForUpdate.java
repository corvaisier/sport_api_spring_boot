package com.julien.sportapi.dto.coach;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CoachDtoForUpdate {
    private String currentName;
    private String newName;
    private String currentPassword;
    private String newPassword;
}

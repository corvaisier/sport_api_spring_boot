package com.julien.sportapi.dto.coach;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class SignUpCoach {
    private String name;
    private String password;
}

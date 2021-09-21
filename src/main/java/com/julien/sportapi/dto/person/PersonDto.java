package com.julien.sportapi.dto.person;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Email;

@AllArgsConstructor
@Data
public class PersonDto {
    @NonNull
    private String name;
    @NonNull
    private String firstName;
    @NonNull
    @Email
    private String email;
    @NonNull
    private String password;
}

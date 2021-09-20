package com.julien.sportapi.dto.person;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class PersonDto {
    private String name;
    private String firstName;
    private String email;
    private String password;
}

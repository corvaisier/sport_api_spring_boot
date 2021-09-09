package com.julien.sportapi.dto.person;


import lombok.Data;

@Data
public class SignUpPerson {
    private String name;
    private String firstName;
    private String email;
    private String password;
}

package com.julien.sportapi.dto;

import lombok.Data;

@Data
public class SignUpPerson {
    private String personName;
    private String personLogin;
    private String personPassword;
    private String personStatus;
}

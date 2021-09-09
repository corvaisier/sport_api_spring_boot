package com.julien.sportapi.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @Column(length = 36)
    @org.hibernate.annotations.Type(type = "uuid-char")
    private UUID id;
    @Column(nullable = false)
    @NotBlank(message = "Name is mandatory")
    private String name;
    @Column(nullable = false)
    @NotBlank(message = "FirstName is mandatory")
    private String firstName;
    @Column(unique = true, nullable = false)
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;
    @Column(nullable = false)
    @NotBlank(message = "Password is mandatory")
    private String password;
    @Column(nullable = false)
    private String status;
    @ManyToMany(mappedBy = "persons")
    private List<Coach> coaches;
}


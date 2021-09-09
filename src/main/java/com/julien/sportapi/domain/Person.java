package com.julien.sportapi.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private String name;
    private String firstName;
    @Column(unique = true)
    private String email;
    private String password;
    @ManyToMany(mappedBy = "persons")
    private List<Coach> coaches;
}


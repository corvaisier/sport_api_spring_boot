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
    private UUID personId;
    private String personName;
    @Column(unique = true)
    private String personLogin;
    private String personPassword;
    private String personStatus;
    @ManyToMany(mappedBy = "persons")
    private List<Coach> coaches;
}

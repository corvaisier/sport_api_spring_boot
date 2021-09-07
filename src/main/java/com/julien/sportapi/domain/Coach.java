package com.julien.sportapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Coach {
    @Id
    @Column(length = 36)
    @org.hibernate.annotations.Type(type = "uuid-char")
    private UUID coachId;
    private String coachName;
    @ManyToMany
    @JoinTable(
            name = "coach_person",
            joinColumns = @JoinColumn(name = "coach_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    private List<Person> persons;

}

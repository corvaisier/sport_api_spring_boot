package com.julien.sportapi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Coach {
    @Id
    @Column(length = 36)
    @org.hibernate.annotations.Type(type = "uuid-char")
    private UUID id;
    @Column(nullable = false)
    @NotBlank(message = "Name is mandatory")
    private String name;
    @Column(nullable = false)
    @NotBlank(message = "Password is mandatory")
    private String password;
    @ManyToMany
    @JoinTable(
            name = "coach_person",
            joinColumns = @JoinColumn(name = "coach_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id"))
    @JsonIgnore
    private List<Person> persons;
    @OneToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Lesson> lessons;

}

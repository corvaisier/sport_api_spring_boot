package com.julien.sportapi.domain;

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
public class Lesson {
    @Id
    @Column(length = 36)
    @org.hibernate.annotations.Type(type = "uuid-char")
    private UUID id;
    @Column(nullable = false)
    @NotBlank(message = "Day is mandatory")
    private String day;
    @Column(nullable = false)
    @NotBlank(message = "Hour is mandatory")
    private String hour;
    @Column(nullable = false)
    @NotBlank(message = "Name is mandatory")
    private String name;
    @Column(nullable = false)
    @NotBlank(message = "difficulty is mandatory")
    private String difficulty;
    @ManyToMany(mappedBy = "lessons")
    private List<Person> persons;
}

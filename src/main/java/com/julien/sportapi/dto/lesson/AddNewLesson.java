package com.julien.sportapi.dto.lesson;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AddNewLesson {
    private String day;
    private String hour;
    private String name;
    private String difficulty;
}

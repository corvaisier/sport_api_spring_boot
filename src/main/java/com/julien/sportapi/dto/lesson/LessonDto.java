package com.julien.sportapi.dto.lesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@AllArgsConstructor
@Data
public class LessonDto {
    @NonNull
    private String day;
    @NonNull
    private String hour;
    @NonNull
    private String name;
    @NonNull
    private String difficulty;
}

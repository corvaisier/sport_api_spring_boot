package com.julien.sportapi.dto.lesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.util.UUID;

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
    @NonNull
    private UUID coachId;
}

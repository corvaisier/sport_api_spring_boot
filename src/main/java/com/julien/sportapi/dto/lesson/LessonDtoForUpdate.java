package com.julien.sportapi.dto.lesson;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@AllArgsConstructor
@Data
public class LessonDtoForUpdate {
    @NonNull
    private String currentDay;
    @NonNull
    private String newDay;
    @NonNull
    private String currentHour;
    @NonNull
    private String newHour;
    @NonNull
    private String currentName;
    @NonNull
    private String newName;
    @NonNull
    private String currentDifficulty;
    @NonNull
    private String newDifficulty;
}
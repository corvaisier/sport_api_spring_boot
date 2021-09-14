package com.julien.sportapi.dao.Lesson;

import com.julien.sportapi.domain.Lesson;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface LessonDao {
    List<Lesson> findAll();
    List<Lesson> findByName(String name);
    List<Lesson> findByDay(String day);
    List<Lesson> findByHour(String hour);
    List<Lesson> findLessonByCoach_Id(UUID id);
    Optional<Lesson> findById(UUID id);
    void add(Lesson lesson);
    void delete(Lesson lesson);
}

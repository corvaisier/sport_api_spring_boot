package com.julien.sportapi.repository;

import com.julien.sportapi.domain.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LessonRepository extends JpaRepository<Lesson, UUID> {
        List<Lesson> findLessonByName(String name);
        List<Lesson> findLessonByDay(String day);
        List<Lesson> findLessonByHour(String hour);
}

package com.julien.sportapi.dao.Lesson;

import com.julien.sportapi.domain.Lesson;
import com.julien.sportapi.repository.LessonRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@AllArgsConstructor
public class LessonDaoInDb implements LessonDao{
    private final LessonRepository lessonRepository;


    @Override
    public List<Lesson> findAll() {
        return lessonRepository.findAll();
    }

    @Override
    public List<Lesson> findByName(String name) {
        return lessonRepository.findLessonByName(name);
    }

    @Override
    public List<Lesson> findByDay(String day) {
        return lessonRepository.findLessonByDay(day);
    }

    @Override
    public List<Lesson> findByHour(String hour) {
        return lessonRepository.findLessonByHour(hour);
    }

    @Override
    public List<Lesson> findLessonByCoach_Id(UUID id) {
        return lessonRepository.findLessonByCoach_Id(id);
    }

    @Override
    public Optional<Lesson> findById(UUID id) {
        return lessonRepository.findById(id);
    }

    @Override
    public void add(Lesson lesson) {
        lessonRepository.save(lesson);
    }

    @Override
    public void delete(Lesson lesson) {
        lessonRepository.delete(lesson);
    }

}

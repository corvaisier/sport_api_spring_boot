package com.julien.sportapi.service;


import com.julien.sportapi.dao.Lesson.LessonDao;
import com.julien.sportapi.domain.Lesson;
import com.julien.sportapi.dto.Lesson.AddNewLesson;
import com.julien.sportapi.exception.LessonException.LessonByIdNotFoundException;
import com.julien.sportapi.exception.LessonException.LessonDateTimeNotValideException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LessonService {
    private final LessonDao lessonDao;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public List<Lesson> findAll() {
        return lessonDao.findAll();
    }

    public List<Lesson> findByName(String name) {
        return lessonDao.findByName(name);
    }

    public List<Lesson> findByDay(String day) {
        return lessonDao.findByDay(day);
    }

    public List<Lesson> findByHour(String hour) {
        return lessonDao.findByHour(hour);
    }

    public Lesson findById(UUID id) {
        return lessonDao.findById(id).orElseThrow(() -> new LessonByIdNotFoundException(id));
    }

    public void add(AddNewLesson addNewLesson) throws LessonDateTimeNotValideException {
        if(!isLessonAvailable(addNewLesson)) {
            throw new LessonDateTimeNotValideException(addNewLesson.getHour(), addNewLesson.getDay() );
        } else {
            Lesson newLesson = Lesson.builder()
                    .id(UUID.randomUUID())
                    .day(addNewLesson.getDay())
                    .hour(addNewLesson.getHour())
                    .name(addNewLesson.getName())
                    .difficulty(addNewLesson.getDifficulty())
                    .build();
            lessonDao.add(newLesson);
            logger.info("create new user : {}", newLesson);
        }
    }

    public void delete(UUID id) {
        Lesson lessonToDelete = lessonDao.findById(id).orElseThrow(() -> new LessonByIdNotFoundException(id));
        lessonDao.delete(lessonToDelete);
        logger.info("delete lesson : {}" + lessonToDelete.getId());

    }

    public void update(UUID id) {
        Lesson lessonToUpdate = lessonDao.findById(id).orElseThrow(() -> new LessonByIdNotFoundException(id));
        lessonDao.add(lessonToUpdate);
        logger.info("update lesson : {}" + lessonToUpdate.getId());
    }

    private Boolean isLessonAvailable(AddNewLesson addNewLesson) {
        return lessonDao.findByHour(addNewLesson.getHour()).isEmpty() && lessonDao.findByDay(addNewLesson.getDay()).isEmpty();
    }
}

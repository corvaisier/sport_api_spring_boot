package com.julien.sportapi.service;


import com.julien.sportapi.dao.Lesson.LessonDao;
import com.julien.sportapi.domain.Lesson;
import com.julien.sportapi.dto.lesson.AddNewLesson;
import com.julien.sportapi.dto.general.UuId;
import com.julien.sportapi.exception.LessonException.LessonByIdNotFoundException;
import com.julien.sportapi.exception.LessonException.LessonDateTimeNotValidException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
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


    public List<Lesson> findLessonByDay(String day) {
        return lessonDao.findByDay(day);
    }

    public List<Lesson> findLessonByhour(String hour) {
        return lessonDao.findByHour(hour);
    }

    public List<Lesson> findLessonByCoachId(UuId id) {
        return lessonDao.findLessonByCoach_Id(id.getId());
    }

    public void addLesson(AddNewLesson addNewLesson) throws LessonDateTimeNotValidException {
        if(!isLessonAvailable(addNewLesson)) {
            throw new LessonDateTimeNotValidException(addNewLesson.getHour(), addNewLesson.getDay() );
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

    public void deleteLesson(UuId id) {
        Lesson lessonToDelete = lessonDao.findById(id.getId()).orElseThrow(() -> new LessonByIdNotFoundException(id.getId()));
        lessonDao.delete(lessonToDelete);
        logger.info("delete lesson : {}", id.getId());

    }

    public void updateLesson(AddNewLesson lesson) {
        List<Lesson> lessonFirstFilter = lessonDao.findByHour(lesson.getHour());
        List<Lesson> lessonSecondFilter = lessonDao.findByDay(lesson.getDay());
        Set<Lesson> lessonToUpdate = new HashSet<>();
        lessonToUpdate.addAll(lessonFirstFilter);
        lessonToUpdate.addAll(lessonSecondFilter);
        lessonDao.add(lessonToUpdate.iterator().next());
        logger.info("update lesson : {}",  lessonToUpdate.iterator().next().getId());
    }

    private Boolean isLessonAvailable(AddNewLesson addNewLesson) {
        return lessonDao.findByHour(addNewLesson.getHour()).isEmpty() && lessonDao.findByDay(addNewLesson.getDay()).isEmpty();
    }
}

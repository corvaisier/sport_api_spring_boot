package com.julien.sportapi.service;


import com.julien.sportapi.dao.Lesson.LessonDao;
import com.julien.sportapi.domain.Lesson;
import com.julien.sportapi.dto.lesson.LessonDto;
import com.julien.sportapi.dto.general.UuId;
import com.julien.sportapi.dto.lesson.LessonDtoForUpdate;
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

    public void addLesson(LessonDto lessonDto) throws LessonDateTimeNotValidException {
        if(!isLessonAvailable(lessonDto)) {
            throw new LessonDateTimeNotValidException(lessonDto.getHour(), lessonDto.getDay() );
        } else {
            Lesson newLesson = Lesson.builder()
                    .id(UUID.randomUUID())
                    .day(lessonDto.getDay())
                    .hour(lessonDto.getHour())
                    .name(lessonDto.getName())
                    .difficulty(lessonDto.getDifficulty())
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

    public void updateLesson(LessonDtoForUpdate lessonDtoForUpdate) {
        List<Lesson> lessonFirstFilter = lessonDao.findByHour(lessonDtoForUpdate.getCurrentHour());
        List<Lesson> lessonSecondFilter = lessonDao.findByDay(lessonDtoForUpdate.getCurrentDay());
        Set<Lesson> lessonToUpdate = new HashSet<>();
        lessonToUpdate.addAll(lessonFirstFilter);
        lessonToUpdate.addAll(lessonSecondFilter);
        lessonDao.add(lessonToUpdate.iterator().next());
        logger.info("update lesson : {}",  lessonToUpdate.iterator().next().getId());
    }

    private Boolean isLessonAvailable(LessonDto lessonDto) {
        return lessonDao.findByHour(lessonDto.getHour()).isEmpty() && lessonDao.findByDay(lessonDto.getDay()).isEmpty();
    }
}

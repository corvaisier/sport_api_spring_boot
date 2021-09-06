package com.julien.sportapi.service;

import com.julien.sportapi.dao.User.UserDao;
import com.julien.sportapi.domain.User;
import com.julien.sportapi.exception.CoachException.CoachIdNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserDao userDao;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> findAll() { return userDao.findAll();}

    public User findById(UUID userId) {
        return userDao.findById(userId).orElseThrow(() -> new CoachIdNotFoundException(userId));
    }

    public void add(String userName, String userLogin, String userPassword, String userStatus) {
        User newUser = new User(UUID.randomUUID(), userName, userLogin, userPassword, userStatus);
        userDao.add(newUser);
        logger.info("create new user : {}", newUser);
    }

    public void delete (UUID userId) {
        User userToDelete = userDao.findById(userId).orElseThrow(() -> new CoachIdNotFoundException(userId));
        userDao.delete(userToDelete);
        logger.info("delete user : {}", userToDelete);
    }

}

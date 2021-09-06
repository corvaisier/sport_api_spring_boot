package com.julien.sportapi.dao.User;

import com.julien.sportapi.domain.User;

import java.util.List;
import java.util.UUID;

public interface UserDao {
    List<User> findAll();
    User findById(UUID userId);
    void add(User user);
    void delete(User user);
//    void update(UUID userId, String );
}

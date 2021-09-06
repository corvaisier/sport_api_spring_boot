package com.julien.sportapi.dao.User;

import com.julien.sportapi.domain.User;
import com.julien.sportapi.exception.CoachException.CoachIdNotFoundException;
import com.julien.sportapi.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class UserDaoInMemory implements UserDao{
    private final UserRepository userRepository;

    public UserDaoInMemory(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(UUID userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void add(User user) {
        userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }
}

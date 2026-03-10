package ru.kata.spring.boot_security.demo.services;

import java.util.List;
import ru.kata.spring.boot_security.demo.models.User;

public interface UserService {
    void save(User user);

    void updateUser(User user);

    User findByUsername(String username);

    User findById(Long id);

    List<User> findAll();

    void delete(Long id);
}

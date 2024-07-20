package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserService extends UserDetailsService {
    User findByLastName(String username);

    void saveUser(User user);

    void update(Long id, User updatedUser);

    void deleteById(Long id);

    List<User> findAll();

    User findById(Long id);
}
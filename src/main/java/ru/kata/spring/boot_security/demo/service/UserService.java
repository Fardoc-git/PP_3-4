package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface UserService extends UserDetailsService {
    //    @Transactional
    User findByLastName(String lastname);

    //    @Transactional
    Optional<User> findById(Long id);

    //    @Transactional
    List<User> findAll();

    @Transactional
    void saveUser(User user);

    @Transactional
    void deleteById(Long id);

    default Collection<? extends GrantedAuthority> getGrantedAuthorities(Collection<Role> roles) {
        return roles
                .stream()
                .map(r -> new SimpleGrantedAuthority(r.getRole())).collect(Collectors.toList());
    }
}

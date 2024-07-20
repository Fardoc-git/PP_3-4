package ru.kata.spring.boot_security.demo.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class DataBaseInit {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @PostConstruct
    public void init() {
        if (roleRepository.findByRole("ROLE_ADMIN").isEmpty()) {
            Role adminRole = new Role();
            adminRole.setRole("ROLE_ADMIN");
            roleRepository.save(adminRole);
            Role userRole = new Role();
            userRole.setRole("ROLE_USER");
            roleRepository.save(userRole);
            User adminUser = new User();
            adminUser.setFirstName("admin");
            adminUser.setLastName("admin");
            adminUser.setPassword(encoder.encode("100"));
            adminUser.setRoles(Set.of(adminRole, userRole));
            userRepository.save(adminUser);
            User regularUser = new User();
            regularUser.setFirstName("user");
            regularUser.setLastName("user");
            regularUser.setPassword(encoder.encode("200"));
            regularUser.setRoles(Set.of(userRole));
            userRepository.save(regularUser);
        }
    }
}
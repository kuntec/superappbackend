package com.example.demo.config;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner load(RoleRepository roleRepo, UserRepository userRepo) {
        return args -> {

            if (roleRepo.findByName("ADMIN") == null) {
                Role admin = new Role();
                admin.setName("ADMIN");
                roleRepo.save(admin);
            }

            if (userRepo.findByEmail("admin@superapp.com").isEmpty()) {
                User adminUser = new User();
                adminUser.setEmail("admin@superapp.com");
                adminUser.setFullName("Super Admin");
                adminUser.setPassword(new BCryptPasswordEncoder().encode("Admin@123"));
                adminUser.setRoles(Set.of(roleRepo.findByName("ADMIN")));
                userRepo.save(adminUser);
            }
        };
    }

}

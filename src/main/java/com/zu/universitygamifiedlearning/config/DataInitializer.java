package com.zu.universitygamifiedlearning.config;


import com.zu.universitygamifiedlearning.model.Role;
import com.zu.universitygamifiedlearning.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initRoles(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.findAll().isEmpty()) {
                Role adminRole = new Role();
                adminRole.setName(Role.RoleName.ROLE_ADMIN);

                Role userRole = new Role();
                userRole.setName(Role.RoleName.ROLE_USER);

                roleRepository.save(adminRole);
                roleRepository.save(userRole);
            }
        };
    }
}

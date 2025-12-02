package com.busbookingsystem.service;

import com.busbookingsystem.entity.Users;
import com.busbookingsystem.enums.Role;
import com.busbookingsystem.repository.UsersDetailsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminUserIntinalizer {
    @Bean
    public CommandLineRunner createAdminUser(UsersDetailsRepository usersDetailsRepository, PasswordEncoder passwordEncoder){
        return args -> {
            if(usersDetailsRepository.findByUsername("admin").isEmpty()){
                Users admin= new Users();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin1234"));
                admin.setRole(Role.ROLE_ADMIN);
                usersDetailsRepository.save(admin);
                System.out.println("default admin created");
            }
            if(usersDetailsRepository.findByUsername("user").isEmpty()){
                Users admin= new Users();
                admin.setUsername("user");
                admin.setPassword(passwordEncoder.encode("user1234"));
                admin.setRole(Role.ROLE_USER);
                usersDetailsRepository.save(admin);
                System.out.println("default user created");
            }
        };
    }
}

package com.busbookingsystem.service;

import com.busbookingsystem.dto.RegisterUserRequest;
import com.busbookingsystem.dto.RegisterUserResponse;
import com.busbookingsystem.entity.Users;
import com.busbookingsystem.exception.NotFoundException;
import com.busbookingsystem.mapper.RegisterUserMapper;
import com.busbookingsystem.repository.UsersDetailsRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterUserService {
    private final UsersDetailsRepository usersDetailsRepository;
    private final PasswordEncoder passwordEncoder;


    public RegisterUserService(UsersDetailsRepository usersDetailsRepository, PasswordEncoder passwordEncoder) {
        this.usersDetailsRepository = usersDetailsRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public RegisterUserResponse registerUser(RegisterUserRequest registerUserRequest){
        if(registerUserRequest.getUsername() == null || registerUserRequest.getUsername().isBlank() ||
                registerUserRequest.getPassword() == null || registerUserRequest.getPassword().isBlank()) {
            throw new NotFoundException("Username and password cannot be empty");
        }
        if(registerUserRequest.getEmail()==null|| registerUserRequest.getEmail().isBlank()){
            throw new NotFoundException("email cannot be empty");
        }
        if(usersDetailsRepository.findByUsername(registerUserRequest.getUsername()).isPresent()){
            throw new NotFoundException("user already exist");
        }

        Users users= new Users();
        users.setUsername(registerUserRequest.getUsername());
        users.setEmail(registerUserRequest.getEmail());
        users.setPassword(passwordEncoder.encode(registerUserRequest.getPassword()));
        users.setRole(registerUserRequest.getRole());
        usersDetailsRepository.save(users);
        return RegisterUserMapper.toDto(users);
    }
}

package com.busbookingsystem.controller;

import com.busbookingsystem.dto.RegisterUserRequest;
import com.busbookingsystem.dto.RegisterUserResponse;
import com.busbookingsystem.enums.Role;
import com.busbookingsystem.service.RegisterUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
@Tag(name = "RegisterUserController")
public class RegisterUserController {
    private final RegisterUserService registerUserService;

    public RegisterUserController(RegisterUserService registerUserService) {
        this.registerUserService = registerUserService;
    }
    @PostMapping("/user")
    public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody RegisterUserRequest registerUserRequest){
        registerUserRequest.setRole(Role.ROLE_USER);
        RegisterUserResponse registerUserResponse=registerUserService.registerUser(registerUserRequest);
       // registerUserResponse.setRole(Role.ROLE_USER);
        return ResponseEntity.ok(registerUserResponse);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin")
    public ResponseEntity<RegisterUserResponse> registerAdmin(@RequestBody RegisterUserRequest registerUserRequest){
        RegisterUserResponse registerUserResponse=registerUserService.registerUser(registerUserRequest);
        return ResponseEntity.ok(registerUserResponse);
    }
}

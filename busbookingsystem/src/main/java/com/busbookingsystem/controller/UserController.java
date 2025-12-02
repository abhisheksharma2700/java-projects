package com.busbookingsystem.controller;

import com.busbookingsystem.dto.UserRequestDto;
import com.busbookingsystem.dto.UserResponseDto;
import com.busbookingsystem.service.UserEntityService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final  UserEntityService userEntityService;

    public UserController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }

    @PostMapping("/addUser")
    public UserResponseDto addUser(@RequestBody UserRequestDto userRequestDto) {
        return userEntityService.addUser(userRequestDto);

    }

    @GetMapping("/getAllUser")
    public List<UserResponseDto> getAllUser() {
        return userEntityService.getAllUsers();
    }

    @GetMapping("/getUser/{id}")
    public UserResponseDto getUserById(@PathVariable Long id) {
        return userEntityService.getUserById(id);
    }

    @PutMapping("/update/{id}")
    public UserResponseDto updateUserById(@PathVariable Long id, @RequestBody UserRequestDto userRequestDto) {
        return userEntityService.updateUserById(id, userRequestDto);
    }

    @DeleteMapping("delete/{id}")
    public String deleteUserById(@PathVariable Long id) {
        return userEntityService.deleteUserById(id);
    }
}

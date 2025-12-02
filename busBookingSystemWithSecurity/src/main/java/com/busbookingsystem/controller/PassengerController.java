package com.busbookingsystem.controller;

import com.busbookingsystem.dto.PassengerRequestDto;
import com.busbookingsystem.dto.PassengerResponseDto;
import com.busbookingsystem.service.PassengerEntityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passenger")
@Tag(name = "PassengerController",description = "add,get,update,delete")
public class PassengerController {
    private PassengerEntityService passengerEntityService;
    private final Logger logger= LoggerFactory.getLogger(PassengerController.class);

    public PassengerController(PassengerEntityService passengerEntityService) {
        this.passengerEntityService = passengerEntityService;
    }

    @PostMapping("/addPassenger")
    public PassengerResponseDto addUser(@Valid @RequestBody PassengerRequestDto passengerRequestDto) {
        logger.info("add user method ");
        return passengerEntityService.addUser(passengerRequestDto);

    }
//    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getAllPassengers")
    public List<PassengerResponseDto> getAllUser() {
        logger.info("getAllPassenger method activited");
        return passengerEntityService.getAllUsers();
    }

    @GetMapping("/getPassenger/{id}")
    public PassengerResponseDto getUserById(@PathVariable Long id) {
        return passengerEntityService.getUserById(id);
    }

    @PutMapping("/update/{id}")
    public PassengerResponseDto updateUserById(@PathVariable Long id, @RequestBody PassengerRequestDto passengerRequestDto) {
        return passengerEntityService.updateUserById(id, passengerRequestDto);
    }

    @DeleteMapping("delete/{id}")
    public String deleteUserById(@PathVariable Long id) {
        return passengerEntityService.deleteUserById(id);
    }
}

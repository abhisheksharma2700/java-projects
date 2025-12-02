package com.busbookingsystem.service;

import com.busbookingsystem.dto.BusRequestDto;
import com.busbookingsystem.dto.BusResponseDto;
import com.busbookingsystem.exception.NotFoundException;
import com.busbookingsystem.repository.BusRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureTestDatabase
public class BusServiceIntgrationTest {

    @Autowired
    private BusService busService;
    @Autowired
    private BusRepository busRepository;
    private BusRequestDto validBusRequest;

    @BeforeEach
    void setUp(){
        validBusRequest=new BusRequestDto();
        validBusRequest.setBusNumber("rj12as0001");
        validBusRequest.setName("bus");
        validBusRequest.setSource("jaipur");
        validBusRequest.setDestination("delhi");
        validBusRequest.setDateOfDeparture( LocalDate.now());
        validBusRequest.setTimeOfDeparture(LocalTime.now());
        //validBusRequest.setTimeOfDeparture(LocalTime.of(1,20,22));
        validBusRequest.setTotalSeats(10);

    }
    @Test
    void shouldAddSuccessfully(){
        BusResponseDto response=busService.addBus(validBusRequest);
        assertNotNull(response);
        assertEquals("bus",response.getName());

    }
    @Test
    void shouldThrowExceptionWhenDuplicationNameFound() {
        busService.addBus(validBusRequest);
        BusRequestDto duplicateName = new BusRequestDto(validBusRequest);
        duplicateName.setBusNumber("dl01as1111");
        Assertions.assertThrows(NotFoundException.class, () -> busService.addBus(duplicateName));


    }
}

package com.busbookingsystem.controller;

import com.busbookingsystem.dto.BusRequestDto;
import com.busbookingsystem.dto.BusResponseDto;
import com.busbookingsystem.filter.JwtAuthFilter;
import com.busbookingsystem.service.BusService;
import com.busbookingsystem.utility.JwtUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(value = BusController.class)
@AutoConfigureMockMvc(addFilters = false)
public class BusControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private JwtUtility jwtUtility;
    @MockBean
    private JwtAuthFilter jwtAuthFilter;
    @MockBean
    private BusService busService;
    private BusResponseDto outputBusResponse;

    @BeforeEach
    void setUp(){
         outputBusResponse= new BusResponseDto();
        outputBusResponse.setBusNumber("rj14as2700");
        outputBusResponse.setName("jaipur express");
        outputBusResponse.setDestination("kasol");
        outputBusResponse.setSource("jaipur");
        outputBusResponse.setTotalSeats(10);

    }
    @Test
    void addbus() throws Exception {
        BusRequestDto inputBusRequest= new BusRequestDto();
        inputBusRequest.setName("jaipur express");
        inputBusRequest.setBusNumber("rj14as2700");
        inputBusRequest.setDestination("kasol");
        inputBusRequest.setSource("jaipur");
        inputBusRequest.setTotalSeats(10);

        Mockito.when(busService.addBus(Mockito.any(BusRequestDto.class)))
                .thenReturn(outputBusResponse);
        mockMvc.perform(MockMvcRequestBuilders.post("/bus/addbus")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                                {
                                  "name": "jaipur express",
                                  "busNumber": "rj14as2700",
                                  "source": "jaipur",
                                  "destination": "kasol",
                                  "totalSeats": 10
                                }
                                """))
                .andExpect(MockMvcResultMatchers.status().isOk())
                 .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("jaipur express"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.busNumber").value("rj14as2700"));




    }
    @Test
    void getBusById() throws Exception{
        Mockito.when(busService.getBusById(1L)).thenReturn(outputBusResponse);
        mockMvc.perform(MockMvcRequestBuilders.get("/bus/getbus/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name")
                        .value(outputBusResponse.getName()));
    }

}

package com.busbookingsystem.controller;

import com.busbookingsystem.repository.BusRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class BusControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private BusRepository busRepository;

    @BeforeEach
    void setUp(){
        busRepository.deleteAll();
    }
    @Test
    void shouldAddBusSuccessfully() throws Exception{
        String requestBody= """
                                {
                                  "name": "jaipur express",
                                  "busNumber": "rj14as2700",
                                  "source": "jaipur",
                                  "destination": "kasol",
                                  "dateOfDeparture": "2025-11-10",
                                  "timeOfDeparture": "12:30:00",
                                  "totalSeats": 10
                                }
                                """;
        mockMvc.perform(MockMvcRequestBuilders.post("/bus/addbus")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.busNumber").value("rj14as2700"));
          assertEquals(1,busRepository.count());

    }

}

package com.busbookingsystem.service;

import com.busbookingsystem.dto.BusRequestDto;
import com.busbookingsystem.dto.BusResponseDto;
import com.busbookingsystem.entity.BusEntity;
import com.busbookingsystem.exception.NotFoundException;
import com.busbookingsystem.mapper.BusMapper;
import com.busbookingsystem.repository.BookingRepository;
import com.busbookingsystem.repository.BusRepository;
import com.busbookingsystem.service.implementation.BusServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BusServiceImplementationTest {

    @Mock
    private BusRepository busRepository;

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BusServiceImplementation busService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private BusRequestDto sampleRequest() {
        BusRequestDto dto = new BusRequestDto();
        dto.setName("Express");
        dto.setBusNumber("RJ14-1234");
        dto.setSource("Jaipur");
        dto.setDestination("Delhi");
        dto.setDateOfDeparture(LocalDate.now());
        dto.setTimeOfDeparture(LocalTime.of(10, 30));
        dto.setTotalSeats(40);
        return dto;
    }

    private BusEntity sampleEntity() {
        BusEntity bus = new BusEntity();
        bus.setId(1L);
        bus.setName("Express");
        bus.setBusNumber("RJ14-1234");
        bus.setSource("Jaipur");
        bus.setDestination("Delhi");
        bus.setTotalSeats(40);
        return bus;
    }

    @Test
    void addBus_success() {
        BusRequestDto request = sampleRequest();
        BusEntity entity = sampleEntity();

        when(busRepository.existsByName("Express")).thenReturn(false);
        when(busRepository.existsByBusNumber("RJ14-1234")).thenReturn(false);
        when(busRepository.save(any(BusEntity.class))).thenReturn(entity);

        BusResponseDto response = busService.addBus(request);

        assertEquals("Express", response.getName());
        verify(busRepository, times(1)).save(any());
    }

    @Test
    void addBus_duplicateName_throwsException() {
        BusRequestDto request = sampleRequest();

        when(busRepository.existsByName("Express")).thenReturn(true);

        assertThrows(NotFoundException.class, () -> busService.addBus(request));
    }

    @Test
    void addBus_invalidSeats_throwsException() {
        BusRequestDto request = sampleRequest();
        request.setTotalSeats(0);

        assertThrows(NotFoundException.class, () -> busService.addBus(request));
    }

    @Test
    void getBusById_success() {
        BusEntity entity = sampleEntity();

        when(busRepository.findById(1L)).thenReturn(Optional.of(entity));

        BusResponseDto response = busService.getBusById(1L);

        assertEquals("Express", response.getName());
    }

    @Test
    void getBusById_notFound() {
        when(busRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> busService.getBusById(1L));
    }


    @Test
    void getAllBus_success() {
        BusEntity entity = sampleEntity();

        when(busRepository.findAll()).thenReturn(Arrays.asList(entity));

        assertEquals(1, busService.getAllBus().size());
    }


    @Test
    void updateBusById_success() {
        BusRequestDto request = sampleRequest();
        BusEntity existing = sampleEntity();

        when(busRepository.findById(1L)).thenReturn(Optional.of(existing));

        BusResponseDto response = busService.updateBusById(1L, request);

        assertEquals("Express", response.getName());
        verify(busRepository, times(1)).save(existing);
    }

    @Test
    void updateBusById_notFound() {
        when(busRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> busService.updateBusById(1L, sampleRequest()));
    }


    @Test
    void deleteByBusId_success() {
        BusEntity entity = sampleEntity();

        when(busRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(bookingRepository.countByBus(entity)).thenReturn(0L);

        String result = busService.deleteByBusId(1L);

        assertEquals("bus deleted", result);
        verify(busRepository, times(1)).delete(entity);
    }

    @Test
    void deleteByBusId_bookedSeatsExist() {
        BusEntity entity = sampleEntity();

        when(busRepository.findById(1L)).thenReturn(Optional.of(entity));
        when(bookingRepository.countByBus(entity)).thenReturn(5L);

        assertThrows(NotFoundException.class, () -> busService.deleteByBusId(1L));
    }



    @Test
    void getBusByName_success() {
        BusEntity entity = sampleEntity();
        when(busRepository.findByName("Express")).thenReturn(Optional.of(entity));

        BusResponseDto dto = busService.getBusByName("Express");

        assertEquals("Express", dto.getName());
    }

    @Test
    void getBusByName_notFound() {
        when(busRepository.findByName("Express")).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> busService.getBusByName("Express"));
    }
}

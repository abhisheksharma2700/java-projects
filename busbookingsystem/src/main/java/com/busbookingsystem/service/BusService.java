package com.busbookingsystem.service;

import com.busbookingsystem.dto.BusRequestDto;
import com.busbookingsystem.dto.BusResponseDto;

import java.util.List;

public interface BusService {
    public BusResponseDto addBus(BusRequestDto busRequestDto);

    List<BusResponseDto> getAllBus();

    public BusResponseDto getBusById(Long id);

    public BusResponseDto updateBusById(Long id, BusRequestDto busRequestDto);

    public String deleteByBusId(Long id);

    public BusResponseDto getBusByName(String name);

    public List<BusResponseDto> getBusBySource(String source);

    public List<BusResponseDto> getBusByDestination(String destination);
}

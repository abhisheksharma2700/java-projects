package com.busbookingsystem.controller;

import com.busbookingsystem.dto.BusRequestDto;
import com.busbookingsystem.dto.BusResponseDto;
import com.busbookingsystem.service.BusService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bus")
@Tag(name="BusController")
public class BusController {
    @Autowired
    private BusService busService;
   // @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addBus")
    public BusResponseDto addBus(@Valid @RequestBody BusRequestDto busRequestDto) {
        return busService.addBus(busRequestDto);

    }

    @GetMapping("/getAllBuses")
    @PreAuthorize("hasAuthority('READ')")
    public List<BusResponseDto> getAllBus() {
        return busService.getAllBus();
    }

    @GetMapping("/getBus/{id}")
    public BusResponseDto getBusById(@PathVariable Long id) {
        return busService.getBusById(id);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('WRITE')")
    public BusResponseDto updateBusById(@PathVariable Long id, @RequestBody BusRequestDto busRequestDto) {
        return busService.updateBusById(id, busRequestDto);

    }
    @PreAuthorize("hasRole('ADMIN'")
    @DeleteMapping("/delete/{id}")
    public String deleteBusById(@PathVariable Long id) {
        return busService.deleteByBusId(id);
    }

    @GetMapping("/name/{name}")
    public BusResponseDto getBusByName(@PathVariable String name) {
        return busService.getBusByName(name);
    }

    @GetMapping("/source/{source}")
    public List<BusResponseDto> getBusBySource(@PathVariable String source) {
        return busService.getBusBySource(source);
    }

    @GetMapping("/destination/{destination}")
    public List<BusResponseDto> getBusByDestination(@PathVariable String destination) {
        return busService.getBusByDestination(destination);
    }


}

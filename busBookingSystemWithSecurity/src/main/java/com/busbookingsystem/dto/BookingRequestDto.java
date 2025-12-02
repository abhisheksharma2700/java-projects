package com.busbookingsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class BookingRequestDto {
    @NotNull(message = "bus id cannot be blank")
    private Long busId;
    @NotNull(message = "user id cannot be blank")
    private Long userId;
    private List<PassengerRequestDto> passenger;


    public Long getBusId() {
        return busId;
    }

    public void setBusId(Long busId) {
        this.busId = busId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<PassengerRequestDto> getPassenger() {
        return passenger;
    }

    public void setPassenger(List<PassengerRequestDto> passenger) {
        this.passenger = passenger;
    }
}

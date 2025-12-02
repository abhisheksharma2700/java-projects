package com.busbookingsystem.dto;

import java.time.LocalDateTime;
import java.util.List;

public class BookingResponseDto {
    private Long id;
    private String userName;
    private String busName;
    private LocalDateTime bookingTime;
    private Integer ticketNumber;
    private List<PassengerResponseDto> passenger;

    public List<PassengerResponseDto> getPassenger() {
        return passenger;
    }

    public void setPassengers(List<PassengerResponseDto> passenger) {
        this.passenger = passenger;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    public Integer getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(Integer ticketNumber) {
        this.ticketNumber = ticketNumber;
    }
}
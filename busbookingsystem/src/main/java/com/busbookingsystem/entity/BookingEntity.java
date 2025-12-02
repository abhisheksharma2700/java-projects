package com.busbookingsystem.entity;
import jakarta.persistence.*;
import jakarta.validation.Valid;

import java.time.LocalDateTime;

@Entity
@Table(name = "booking")
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "bus_id", referencedColumnName = "id")
    private BusEntity bus;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    private Integer ticketNumber;
    private Integer seatNumber;
    private LocalDateTime localDateTime;


    public Integer getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(Integer seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BusEntity getBus() {
        return bus;
    }

    public void setBus(BusEntity bus) {
        this.bus = bus;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Integer getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(Integer ticketNumber) {
        this.ticketNumber = ticketNumber;
    }



    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}

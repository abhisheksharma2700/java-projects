package com.busbookingsystem.entity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

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
    private Users user;
    @OneToMany(mappedBy = "booking",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<PassengerEntity> passenger;
    private Integer ticketNumber;

    private LocalDateTime bookingAt;

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
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

    public List<PassengerEntity> getPassenger() {
        return passenger;
    }

    public void setPassenger(List<PassengerEntity> passenger) {
        this.passenger = passenger;
    }

    public Integer getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(Integer ticketNumber) {
        this.ticketNumber = ticketNumber;
    }



    public LocalDateTime getBookingAt() {
        return bookingAt;
    }

    public void setBookingAt(LocalDateTime bookingAt) {
        this.bookingAt = bookingAt;
    }
}

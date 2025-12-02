package com.busbookingsystem.repository;

import com.busbookingsystem.entity.BookingEntity;
import com.busbookingsystem.entity.BusEntity;
import com.busbookingsystem.entity.PassengerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity,Long> {
    Optional<BookingEntity> findByUserIdAndBusId(Long userId, Long busId);
    long countByBus(BusEntity bus);

    long countByPassenger(PassengerEntity existingPassengerEntity);
}

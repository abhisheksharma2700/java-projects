package com.busbookingsystem.repository;

import com.busbookingsystem.entity.PassengerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestH2Repository extends JpaRepository<PassengerEntity,Long> {
}

package com.busbookingsystem.repository;

import com.busbookingsystem.entity.PassengerEntity;
import com.busbookingsystem.enums.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerEntityRepository extends JpaRepository<PassengerEntity,Long> {
    boolean existsByEmail(String email);
    boolean existsByMobileNumber(String mobileNumber);
    boolean existsByGender(Gender gender);
}

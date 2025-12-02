package com.busbookingsystem.repository;

import com.busbookingsystem.entity.BusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusRepository extends JpaRepository<BusEntity,Long> {

    Optional<BusEntity> findByName(String name);
      List<BusEntity> findBySource(String source);
      List <BusEntity> findByDestination(String destination);
      Boolean existsByName(String name);
      Boolean existsByBusNumber(String busNumber);

}

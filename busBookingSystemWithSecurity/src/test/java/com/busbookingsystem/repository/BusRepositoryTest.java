package com.busbookingsystem.repository;

import com.busbookingsystem.entity.BusEntity;
import com.busbookingsystem.exception.NotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class BusRepositoryTest {
    @Autowired
    private BusRepository busRepository;
    @Autowired
    private TestEntityManager testEntityManager;
    private BusEntity busEntity;

    @BeforeEach
    void setUp(){
         busEntity= new BusEntity();
        busEntity.setName("pubjab airlines");
        busEntity.setBusNumber("rj14as0001");
        busEntity.setTotalSeats(10);
        busEntity.setSource("jaipur");
        busEntity.setDestination("uddaipur");
        testEntityManager.persist(busEntity);
    }
    @Test
    public void whenFindById_thenReturnBus(){
        BusEntity bus= busRepository.findByName("pubjab airlines").orElseThrow(()-> new NotFoundException("bus not found with this particular name"));
        assertEquals(busEntity.getBusNumber(),bus.getBusNumber());

    }

    @AfterEach
    void tearDown() {
    }


    @Test
    void findBySource() {
        List<BusEntity> bus= busRepository.findBySource("jaipur");
        assertNotNull(bus);

    }

    @Test
    void findByDestination() {
    }

    @Test
    void existsByName() {
    }

    @Test
    void existsByBusNumber() {
    }
}

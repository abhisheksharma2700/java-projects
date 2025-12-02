package com.busbookingsystem.service;

import com.busbookingsystem.dto.PassengerRequestDto;
import com.busbookingsystem.dto.PassengerResponseDto;
import com.busbookingsystem.entity.PassengerEntity;
import com.busbookingsystem.enums.Gender;
import com.busbookingsystem.exception.NotFoundException;
import com.busbookingsystem.mapper.PassengerMapper;
import com.busbookingsystem.repository.PassengerEntityRepository;
import com.busbookingsystem.service.implementation.PassengerEntityServiceImplementation;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PassengerUnitTest {

    @Mock
    PassengerEntityRepository passengerEntityRepository;

    @InjectMocks
    PassengerEntityServiceImplementation passengerEntityService;
    @BeforeAll
    public static void init(){
        System.out.println("beforeAll");
    }
    @BeforeEach
    public void beforeEach(){
        System.out.println("beforeEach");
    }

    @Test
    void addUserTest() {

        PassengerRequestDto requestDto = new PassengerRequestDto();
        requestDto.setName("ADD passenger");
        requestDto.setAge(29);
        requestDto.setEmail("passenger@gmail.com");
        requestDto.setMobileNumber("1234567890");
        requestDto.setGender(Gender.MALE);

        PassengerEntity passengerEntity = PassengerMapper.toEntity(requestDto);

        Mockito.when(passengerEntityRepository.save(Mockito.any()))
                .thenReturn(passengerEntity);


        PassengerResponseDto dto = passengerEntityService.addUser(requestDto);
        Assertions.assertNotNull(dto);
        Assertions.assertEquals(29,dto.getAge());
        Assertions.assertEquals("ADD passenger", dto.getName());
        System.out.println(dto.getName());
        System.out.println("test case passed");

    }
    @Test
    void getAllPassenger(){
        passengerEntityRepository.findAll().stream().map(passengerEntity -> passengerEntity).collect(Collectors.toList());
    }
    @Test
    void getPassengerById(){
        passengerEntityRepository.findById(1L);

    }
    @Test
    void deleteUserById() {
        PassengerEntity entity = new PassengerEntity();
        entity.setId(1L);

        Mockito.when(passengerEntityRepository.findById(1L))
                .thenReturn(Optional.of(entity));

        passengerEntityService.deleteUserById(1L);

        Mockito.verify(passengerEntityRepository, Mockito.times(1)).delete(entity);
    }
    @AfterAll
    public static void destroy(){
        System.out.println("After all");
    }

    @Test
    void testException(){
        Long number=1L;

        Mockito.when(passengerEntityRepository.findById(number)).thenReturn(Optional.empty());


       NotFoundException notFoundException= assertThrows(NotFoundException.class,()->passengerEntityService.getUserById(number));
        Assertions.assertEquals("user with this is id not found",notFoundException.getMessage());


    }

    @AfterEach
    public void cleanup(){
        System.out.println("After each");
    }


}

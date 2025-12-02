package com.busbookingsystem.service.implementation;

import com.busbookingsystem.dto.BusRequestDto;
import com.busbookingsystem.dto.BusResponseDto;
import com.busbookingsystem.entity.BusEntity;
import com.busbookingsystem.exception.NotFoundException;
import com.busbookingsystem.mapper.BusMapper;
import com.busbookingsystem.repository.BusRepository;
import com.busbookingsystem.service.BusService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusServiceImplementation implements BusService {
    private final BusRepository busRepository;

    public BusServiceImplementation(BusRepository busRepository) {
        this.busRepository = busRepository;
    }

    @Override
    public BusResponseDto addBus(BusRequestDto busRequestDto) {
        if(busRepository.existsByName(busRequestDto.getName())){
            throw new NotFoundException("already having bus with the same name , please enter a different bus name");
        }
        if(busRepository.existsByBusNumber(busRequestDto.getBusNumber())){
            throw  new NotFoundException("bus number must be different from the existing buses ");
        }
        if(busRequestDto.getSource()==null||
                busRequestDto.getDestination()==null||
                busRequestDto.getBusNumber()==null ||
                busRequestDto.getName()==null||
                busRequestDto.getDateOfDeparture()==null|| busRequestDto.getTimeOfDeparture()==null){
            throw new NotFoundException("bus must be having these all fields(busname,busnumber,source,destination,date of departure and time of departure");
        }
        if(busRequestDto.getTotalSeats()<=0){
            throw new NotFoundException("bus must be having seats more than 0");
        }
        BusEntity bus = BusMapper.toEntity(busRequestDto);
        bus = busRepository.save(bus);
        return BusMapper.toDto(bus);
    }

    @Override
    public List<BusResponseDto> getAllBus() {
        return busRepository.findAll().stream().map(bus -> BusMapper.toDto(bus)).collect(Collectors.toList());


    }

    @Override
    public BusResponseDto getBusById(Long id) {
        BusEntity bus = busRepository.findById(id).orElseThrow(() -> new NotFoundException("bus not found with this id:" + id));
        return BusMapper.toDto(bus);


    }

    @Override
    public BusResponseDto updateBusById(Long id, BusRequestDto busRequestDto) {
        BusEntity existingBus = busRepository.findById(id).orElseThrow(() -> new NotFoundException("no bus found with this id" + id));
        if(busRequestDto.getSource()==null||
                busRequestDto.getDestination()==null||
                busRequestDto.getBusNumber()==null ||
                busRequestDto.getName()==null||
                busRequestDto.getDateOfDeparture()==null||
                busRequestDto.getTimeOfDeparture()==null){
            throw new NotFoundException("bus must be having these all fields(busname,busnumber,source,destination,date of departure and time of departure");
        }
        if(busRequestDto.getTotalSeats()<=0){
            throw new NotFoundException("bus must be having seats more than 0");
        }
        existingBus.setName(busRequestDto.getName());
        existingBus.setBusNumber(busRequestDto.getBusNumber());
        existingBus.setSource(busRequestDto.getSource());
        existingBus.setDestination(busRequestDto.getDestination());
        existingBus.setDateOfDeparture(busRequestDto.getDateOfDeparture());
        existingBus.setTimeOfDeparture(busRequestDto.getTimeOfDeparture());
        existingBus.setTotalSeats(busRequestDto.getTotalSeats());
        existingBus.setAvailableSeats(busRequestDto.getAvailableSeats());
        busRepository.save(existingBus);
        return BusMapper.toDto(existingBus);

    }

    @Override
    public String deleteByBusId(Long id) {
        BusEntity bus = busRepository.findById(id).orElseThrow(() -> new NotFoundException("bus not found with this id" + id));
        busRepository.delete(bus);
        return "bus deleted";
    }

    @Override
    public BusResponseDto getBusByName(String name) {
        BusEntity bus = busRepository.findByName(name).orElseThrow(() -> new NotFoundException("not found any bus with this name"));
        return BusMapper.toDto(bus);


    }

    @Override
    public List<BusResponseDto> getBusBySource(String source) {
        List<BusEntity> bus = busRepository.findBySource(source);
        if (bus.isEmpty()) {
            throw new NotFoundException("bus with this particular source: " + source + " not found ");
        }
        return busRepository.findBySource(source).stream().map(BusMapper::toDto).collect(Collectors.toList());

    }

    @Override
    public List<BusResponseDto> getBusByDestination(String destination) {
        List<BusEntity> bus = busRepository.findByDestination(destination);
        if (bus.isEmpty()) {
            throw new NotFoundException("bus with this particular destination: " + destination + " not found");
        }
        return busRepository.findByDestination(destination).stream().map(BusMapper::toDto).collect(Collectors.toList());


    }

}

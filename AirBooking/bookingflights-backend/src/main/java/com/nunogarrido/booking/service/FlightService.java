package com.nunogarrido.booking.service;

import com.nunogarrido.booking.dao.FlightRepository;
import com.nunogarrido.booking.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
@Transactional
@Service
public class FlightService {
    public FlightRepository flightRepository;

    @Autowired
    public FlightService(FlightRepository flightRepository){
        this.flightRepository = flightRepository;
    }

    public Flight save(Flight obj) {
        return flightRepository.save(obj);
    }

    public boolean delete(Flight obj) {
        if (flightRepository.existsById(obj.getId())) {
            flightRepository.delete(obj);
            return true;
        } else {
            return false;
        }
    }

    public void deleteAll(List<Flight> entities) {
        flightRepository.deleteAll();
    }

    public void saveAll(List<Flight> entities) {
        flightRepository.saveAll(entities);
    }

    public List<Flight> findAll() {
        return flightRepository.findAll();
    }

    public boolean deleteById(long id) {
        if (flightRepository.existsById(id)){
            flightRepository.deleteById(id);

            return true;
        } else {
            return false;
        }

    }

    public Flight getOne(long id) {
        return flightRepository.getById(id);
    }

    public List<Flight> getByCustomerId(long id) {
        return flightRepository.findAll().stream().filter(i -> i.getCustomer().getId()==id).collect(Collectors.toList());
    }
    public Flight getByNumber(String string) {
        return flightRepository.findAll().stream().filter(i -> Objects.equals(i.getNumber(), string)).findFirst().orElse(null);

    }


}

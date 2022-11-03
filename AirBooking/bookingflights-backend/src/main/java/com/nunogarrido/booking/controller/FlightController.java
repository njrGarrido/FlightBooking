package com.nunogarrido.booking.controller;

import com.nunogarrido.booking.dto.FlightRequest;
import com.nunogarrido.booking.dto.FlightResponse;
import com.nunogarrido.booking.model.Flight;
import com.nunogarrido.booking.model.Customer;
import com.nunogarrido.booking.service.FlightService;
import com.nunogarrido.booking.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/flights")
public class FlightController {
    private FlightService flightService;
    private CustomerService customerService;
    private ModelMapper modelMapper;

    public FlightController(FlightService flightService, CustomerService customerService, ModelMapper modelMapper) {
        this.flightService = flightService;
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    ResponseEntity getAllFlights() {
        List<FlightResponse> ar = flightService.findAll().stream()
                .map(e->modelMapper.map(e, FlightResponse.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(ar, HttpStatus.ACCEPTED);
    }

    @GetMapping("/{id}")
    ResponseEntity<FlightResponse> getFlightById(@PathVariable Long id) {
        FlightResponse ar = modelMapper.map(flightService.getOne(id), FlightResponse.class);
        return new ResponseEntity<>(ar, HttpStatus.ACCEPTED);
    }

    @GetMapping("/customer/{id}")
    ResponseEntity<List<FlightResponse>> getFlightByCustomerId(@PathVariable Long id) {
        List<FlightResponse> ar = flightService.getByCustomerId(id).stream()
                .map(e->modelMapper.map(e, FlightResponse.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(ar, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteFlightById(@PathVariable Long id) {
        flightService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/customer/{id}")
    ResponseEntity<Void> postCustomerById(@RequestBody @Valid FlightRequest flightRequest, @PathVariable Long id) {
        Flight flight = modelMapper.map(flightRequest, Flight.class);

        Flight flight1 = new Flight();
        flight1.setNumber(UUID.randomUUID().toString());
        flight1.setPrice(flight.getPrice());
        flight1.setOrigin((flight1.getOrigin()));
        flight1.setDestination(flight1.getDestination());
        flight1.setDeparture(flight1.getDeparture());
        flight1.setArrival(flight1.getArrival());

        Customer customer1 = customerService.getOne(id);
        customer1.addFlight(flight1);
        customerService.edit(customer1);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    /*@PostMapping("/addmoney")
    ResponseEntity<FlightResponse> addPriceFlight(@RequestParam("accountnumber") String number, @RequestParam("value") Double value) {
        Double oldBalance = flightService.getByNumber(number).getBalance();
        Double newBalance = oldBalance+value;
        Flight acc = flightService.getByNumber(number);
        acc.setBalance(newBalance);
        flightService.save(acc);
        FlightResponse ar = modelMapper.map(flightService.getByNumber(number), FlightResponse.class);
        return new ResponseEntity<>(ar, HttpStatus.ACCEPTED);
    }

    @PostMapping("/withdraw")
    ResponseEntity<FlightResponse> withdrawMoneyAccount(@RequestParam("accountnumber") String number, @RequestParam("value") Double value) {
        if (flightService.getByNumber(number).getBalance() >= value){
            Double oldBalance = flightService.getByNumber(number).getBalance();
            Double newBalance = oldBalance-value;
            Flight acc = flightService.getByNumber(number);
            acc.setBalance(newBalance);
            FlightResponse ar = modelMapper.map(flightService.getByNumber(number), FlightResponse.class);
            return new ResponseEntity<>(ar, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/transfer")
    ResponseEntity<FlightResponse> transferMoneyAccount(@RequestParam("fromaccountnumber") String fromnumber, @RequestParam("toaccountnumber") String tonumber, @RequestParam("value") Double value) {
        if (flightService.getByNumber(fromnumber).getBalance() >= value){
            Flight accfrom = flightService.getByNumber(fromnumber);
            Double fromvalue = flightService.getByNumber(fromnumber).getBalance();
            Flight accto = flightService.getByNumber(tonumber);
            Double tovalue = flightService.getByNumber(tonumber).getBalance();
            accfrom.setBalance(fromvalue-value);
            accto.setBalance(tovalue+value);
            FlightResponse ar = modelMapper.map(flightService.getByNumber(tonumber), FlightResponse.class);
            return new ResponseEntity<>(ar, HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        */

    }
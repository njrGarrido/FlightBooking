package com.nunogarrido.booking.controller;

import com.nunogarrido.booking.dto.CustomerRequest;
import com.nunogarrido.booking.dto.CustomerResponse;
import com.nunogarrido.booking.model.Flight;
import com.nunogarrido.booking.model.Customer;
import com.nunogarrido.booking.service.CustomerService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private CustomerService customerService;
    private ModelMapper modelMapper;


    public CustomerController(CustomerService customerService, ModelMapper modelMapper) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }



    @GetMapping
        ResponseEntity getAllCustomers(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
             List<CustomerResponse> cr = customerService.getAllCustomersPage(page, size).stream()
                     .map(e->modelMapper.map(e, CustomerResponse.class))
                     .collect(Collectors.toList());
        return new ResponseEntity<>(cr, HttpStatus.ACCEPTED);

    }


    @GetMapping("/{id}")
    ResponseEntity<CustomerResponse> getCustomerById(@PathVariable Long id) {
        CustomerResponse cr = modelMapper.map(customerService.getOne(id), CustomerResponse.class);
        return new ResponseEntity<>(cr, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteCustomerById(@PathVariable Long id) {
        customerService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @PostMapping
    ResponseEntity<CustomerResponse> createCustomer(@RequestBody @Valid CustomerRequest customerRequest) {
        Customer cust = modelMapper.map(customerRequest, Customer.class);

        Customer customer = new Customer();
        customer.setFirstName(cust.getFirstName());
        customer.setAge(cust.getAge());
        customer.setLastName(cust.getLastName());
        customer.setNationality(cust.getNationality());
        customer.setFlights(cust.getFlights());
        CustomerResponse cr = modelMapper.map(customerService.save(customer), CustomerResponse.class);
        return new ResponseEntity<>(cr, HttpStatus.ACCEPTED);
    }

    @PutMapping
    ResponseEntity<Void> editCustomerById(@RequestBody @Valid CustomerRequest customerRequest) {

        Customer customer = modelMapper.map(customerRequest, Customer.class);

        String newFirstName = customer.getFirstName() == null ? customerService.getOne(customer.getId()).getFirstName() : customer.getFirstName();
        String newLastName = customer.getLastName() == null ? customerService.getOne(customer.getId()).getLastName() : customer.getLastName();
        int newAge = customer.getAge() == null ? customerService.getOne(customer.getId()).getAge() : customer.getAge();
        String newNationality = customer.getNationality() == null ? customerService.getOne(customer.getId()).getNationality() : customer.getNationality();
        List<Flight> newList = customer.getFlights() == null ? customerService.getOne(customer.getId()).getFlights() : customer.getFlights();

        Customer result = new Customer();

        result.setId(customer.getId());
        result.setFirstName(newFirstName);
        result.setAge(newAge);
        result.setLastName(newLastName);
        result.setNationality(newNationality);
        result.setFlights(newList);
        customerService.edit(result);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}

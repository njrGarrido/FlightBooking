package com.nunogarrido.booking.dto;

import com.nunogarrido.booking.model.Flight;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {
    private Long id;

    @Size(min = 2, message = "Name must be longer than 2 chars!")
    private String firstName;
    @Size(min = 2, message = "Name must be longer than 2 chars!")
    private String lastName;
    @Size(min = 2, message = "Name must be longer than 2 chars!")
    private String nationality;
    @Min(value = 18, message = "Age can't be less than 18!")
    private Integer age;

    private List<Flight> flights;

}

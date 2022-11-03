package com.nunogarrido.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightResponse {
    private Long id;
    private String number;
    private String origin;
    private String destination;
    private String departure;
    private String arrival;
    private Double price;
}

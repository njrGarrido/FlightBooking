package com.nunogarrido.booking.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name="customer")
public class Customer extends AbstractEntity {

    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    private String nationality;
    @NonNull
    private Integer age;
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Flight> flights;

    public Flight addFlight(Flight flight) {
        flight.setCustomer(this);
        flights.add(flight);
        return flight;
    }


}

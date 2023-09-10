package com.akinserhatgoksel.FlightSearchAPI.dto.request;

import com.akinserhatgoksel.FlightSearchAPI.dto.AirportDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateFlightRequest {
    private Long id;
    private AirportDto departureAirport;
    private AirportDto arrivalAirport;
    private LocalDateTime departureDateTime;
    private LocalDateTime returnDateTime;
    private double price;
}

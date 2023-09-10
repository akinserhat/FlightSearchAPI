package com.akinserhatgoksel.FlightSearchAPI.dto.request;

import com.akinserhatgoksel.FlightSearchAPI.dto.AirportDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateFlightRequest {
    private AirportDto departureAirport;
    private AirportDto arrivalAirport;
    private LocalDateTime departureDateTime;
    private LocalDateTime returnDateTime;
    private double price;
}

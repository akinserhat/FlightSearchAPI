package com.akinserhatgoksel.FlightSearchAPI.controller;

import com.akinserhatgoksel.FlightSearchAPI.dto.FlightDto;
import com.akinserhatgoksel.FlightSearchAPI.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/search")
public class SearchFlightController {

    private final FlightService flightService;

    @GetMapping
    public ResponseEntity<List<FlightDto>> searchFlights(
            @RequestParam Long departureAirportId,
            @RequestParam Long arrivalAirportId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime departureDateTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime returnDateTime) {

        LocalDateTime startDate = departureDateTime;

        List<FlightDto> flightsAfterDate = flightService.findFlightsAfterDate(startDate);
        List<FlightDto> flights = flightService.searchFlights(departureAirportId, arrivalAirportId, departureDateTime, returnDateTime);
        flightsAfterDate.addAll(flights);

        if (flightsAfterDate.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(flightsAfterDate);
        }
    }
}

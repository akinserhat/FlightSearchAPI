package com.akinserhatgoksel.FlightSearchAPI.controller;

import com.akinserhatgoksel.FlightSearchAPI.dto.AirportDto;
import com.akinserhatgoksel.FlightSearchAPI.dto.FlightDto;
import com.akinserhatgoksel.FlightSearchAPI.dto.request.CreateAirportRequest;
import com.akinserhatgoksel.FlightSearchAPI.dto.request.CreateFlightRequest;
import com.akinserhatgoksel.FlightSearchAPI.dto.request.UpdateAirportRequest;
import com.akinserhatgoksel.FlightSearchAPI.dto.request.UpdateFlightRequest;
import com.akinserhatgoksel.FlightSearchAPI.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/flight")
public class FlightController {
 
    private final FlightService flightService;

    @GetMapping
    public ResponseEntity<List<FlightDto>> getAllFlights() {
        return ResponseEntity.ok(flightService.getAllFlights());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightDto> getFlightById(@PathVariable Long id) {
        return ResponseEntity.ok(flightService.getFlightById(id));
    }

    @PostMapping
    public ResponseEntity<FlightDto> createFlight(@RequestBody CreateFlightRequest createFlightRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(flightService.createFlight(createFlightRequest));
    }

    @PutMapping
    public ResponseEntity<FlightDto> updateFlight(@RequestBody UpdateFlightRequest updateFlightRequest) {
        return ResponseEntity.ok(flightService.updateFlight(updateFlightRequest));
    }

    @DeleteMapping("/{id}")
    public void deleteFlightById(@PathVariable Long id) {
        flightService.deleteFlight(id);
    }
}

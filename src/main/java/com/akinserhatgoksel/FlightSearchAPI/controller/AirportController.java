package com.akinserhatgoksel.FlightSearchAPI.controller;

import com.akinserhatgoksel.FlightSearchAPI.dto.AirportDto;
import com.akinserhatgoksel.FlightSearchAPI.dto.request.CreateAirportRequest;
import com.akinserhatgoksel.FlightSearchAPI.dto.request.UpdateAirportRequest;
import com.akinserhatgoksel.FlightSearchAPI.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/airport")
public class AirportController {

    private final AirportService airportService;

    @GetMapping
    public ResponseEntity<List<AirportDto>> getAllAirports() {
        return ResponseEntity.ok(airportService.getAllAirports());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AirportDto> getAirportById(@PathVariable Long id) {
        return ResponseEntity.ok(airportService.getAirportById(id));
    }

    @PostMapping
    public ResponseEntity<AirportDto> createAirport(@RequestBody CreateAirportRequest createAirportRequest) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(airportService.createAirport(createAirportRequest));
    }

    @PutMapping
    public ResponseEntity<AirportDto> updateAirport(@RequestBody UpdateAirportRequest updateAirportRequest) {
        return ResponseEntity.ok(airportService.updateAirport(updateAirportRequest));
    }

    @DeleteMapping("/{id}")
    public void deleteAirportById(@PathVariable Long id) {
        airportService.deleteAirport(id);
    }

}

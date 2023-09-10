package com.akinserhatgoksel.FlightSearchAPI.controller;

import com.akinserhatgoksel.FlightSearchAPI.job.MockDataGenerator;
import com.akinserhatgoksel.FlightSearchAPI.model.Airport;
import com.akinserhatgoksel.FlightSearchAPI.model.Flight;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mock")
public class MockDataController {

    @GetMapping("/airports")
    public List<Airport> getAirports() {
        return MockDataGenerator.generateAirports();
    }
    @GetMapping("/flights")
    public List<Flight> getFlights() {
        return MockDataGenerator.generateFlights();
    }
}

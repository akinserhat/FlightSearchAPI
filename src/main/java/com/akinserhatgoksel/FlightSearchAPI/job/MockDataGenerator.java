package com.akinserhatgoksel.FlightSearchAPI.job;

import com.akinserhatgoksel.FlightSearchAPI.model.Airport;
import com.akinserhatgoksel.FlightSearchAPI.model.Flight;
import com.akinserhatgoksel.FlightSearchAPI.repository.AirportRepository;
import com.akinserhatgoksel.FlightSearchAPI.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MockDataGenerator {
    private final AirportRepository airportRepository;
    private final FlightRepository flightRepository;

    @Scheduled(cron = "0 0 6 * * ?")
    public void initializeMockData() {
        List<Airport> mockAirports = generateAirports();
        List<Flight> mockFlights = generateFlights();
        airportRepository.saveAll(mockAirports);
        flightRepository.saveAll(mockFlights);
    }

    public static List<Airport> generateAirports() {
        List<Airport> airports = new ArrayList<>();

        Airport istanbul = new Airport();
        istanbul.setId(1L);
        istanbul.setCity("Istanbul");

        Airport newYork = new Airport();
        newYork.setId(2L);
        newYork.setCity("New York");

        Airport tokyo = new Airport();
        tokyo.setId(3L);
        tokyo.setCity("Tokyo");

        Airport madrid = new Airport();
        madrid.setId(4L);
        madrid.setCity("Madrid");

        airports.add(istanbul);
        airports.add(newYork);
        airports.add(tokyo);
        airports.add(madrid);

        return airports;
    }

    public static List<Flight> generateFlights() {
        List<Flight> flights = new ArrayList<>();

        Airport istanbul = new Airport();
        istanbul.setId(1L);
        istanbul.setCity("Istanbul");

        Airport newYork = new Airport();
        newYork.setId(2L);
        newYork.setCity("New York");

        Airport tokyo = new Airport();
        tokyo.setId(3L);
        tokyo.setCity("Tokyo");

        Airport madrid = new Airport();
        madrid.setId(4L);
        madrid.setCity("Madrid");

        Flight flight1 = new Flight();
        flight1.setId(1L);
        flight1.setDepartureAirport(istanbul);
        flight1.setArrivalAirport(newYork);
        flight1.setDepartureDateTime(LocalDateTime.now());
        flight1.setReturnDateTime(LocalDateTime.now().plusDays(1));
        flight1.setPrice(1000.0);

        Flight flight2 = new Flight();
        flight2.setId(2L);
        flight2.setDepartureAirport(tokyo);
        flight2.setArrivalAirport(madrid);
        flight2.setDepartureDateTime(LocalDateTime.now().plusDays(2));
        flight2.setPrice(800.0);

        Flight flight3 = new Flight();
        flight3.setId(3L);
        flight3.setDepartureAirport(madrid);
        flight3.setArrivalAirport(istanbul);
        flight3.setDepartureDateTime(LocalDateTime.now().plusDays(1));
        flight3.setPrice(2800.0);

        flights.add(flight1);
        flights.add(flight2);
        flights.add(flight3);

        return flights;
    }
}

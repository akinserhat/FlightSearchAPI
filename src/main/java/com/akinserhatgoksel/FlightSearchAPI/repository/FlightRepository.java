package com.akinserhatgoksel.FlightSearchAPI.repository;

import com.akinserhatgoksel.FlightSearchAPI.model.Airport;
import com.akinserhatgoksel.FlightSearchAPI.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureDateTime(
            Airport departureAirport, Airport arrivalAirport, LocalDateTime departureDateTime);

    List<Flight> findByDepartureAirportAndArrivalAirportAndReturnDateTime(
            Airport arrivalAirport, Airport departureAirport, LocalDateTime returnDateTime);

    List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureDateTimeAndReturnDateTime(
            Airport departureAirport, Airport arrivalAirport, LocalDateTime departureDateTime, LocalDateTime returnDateTime);

    @Query("SELECT f FROM Flight f WHERE f.departureDateTime >= :startDate")
    List<Flight> findFlightsAfterDate(@Param("startDate") LocalDateTime startDate);
}

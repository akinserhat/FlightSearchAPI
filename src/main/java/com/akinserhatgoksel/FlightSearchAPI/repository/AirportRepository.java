package com.akinserhatgoksel.FlightSearchAPI.repository;

import com.akinserhatgoksel.FlightSearchAPI.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}

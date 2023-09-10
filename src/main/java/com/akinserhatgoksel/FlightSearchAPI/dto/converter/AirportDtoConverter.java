package com.akinserhatgoksel.FlightSearchAPI.dto.converter;

import com.akinserhatgoksel.FlightSearchAPI.dto.AirportDto;
import com.akinserhatgoksel.FlightSearchAPI.model.Airport;
import org.springframework.stereotype.Component;

@Component
public class AirportDtoConverter {
    public AirportDto convertToDto(Airport airport) {
        AirportDto airportDto = new AirportDto();
        airportDto.setId(airport.getId());
        airportDto.setCity(airport.getCity());
        return airportDto;
    }

    public Airport convertToEntity(AirportDto airportDto) {
        Airport airport = new Airport();
        airport.setId(airportDto.getId());
        airport.setCity(airportDto.getCity());
        return airport;
    }
}

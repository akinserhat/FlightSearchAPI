package com.akinserhatgoksel.FlightSearchAPI.dto.converter;

import com.akinserhatgoksel.FlightSearchAPI.dto.FlightDto;
import com.akinserhatgoksel.FlightSearchAPI.model.Flight;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FlightDtoConverter {

    private final AirportDtoConverter airportDtoConverter;

    public FlightDto convertToDto(Flight flight) {
        FlightDto flightDto = new FlightDto();
        flightDto.setId(flight.getId());
        flightDto.setDepartureAirport(airportDtoConverter.convertToDto(flight.getDepartureAirport()));
        flightDto.setArrivalAirport(airportDtoConverter.convertToDto(flight.getArrivalAirport()));
        flightDto.setDepartureDateTime(flight.getDepartureDateTime());
        flightDto.setReturnDateTime(flight.getReturnDateTime());
        flightDto.setPrice(flight.getPrice());
        return flightDto;
    }

    public Flight convertToEntity(FlightDto flightDto) {
        Flight flight = new Flight();
        flight.setId(flightDto.getId());
        flight.setDepartureAirport(airportDtoConverter.convertToEntity(flightDto.getDepartureAirport()));
        flight.setArrivalAirport(airportDtoConverter.convertToEntity(flightDto.getArrivalAirport()));
        flight.setDepartureDateTime(flightDto.getDepartureDateTime());
        flight.setReturnDateTime(flightDto.getReturnDateTime());
        flight.setPrice(flightDto.getPrice());
        return flight;
    }
}

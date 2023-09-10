package com.akinserhatgoksel.FlightSearchAPI.service;

import com.akinserhatgoksel.FlightSearchAPI.dto.AirportDto;
import com.akinserhatgoksel.FlightSearchAPI.dto.request.CreateFlightRequest;
import com.akinserhatgoksel.FlightSearchAPI.dto.FlightDto;
import com.akinserhatgoksel.FlightSearchAPI.dto.request.UpdateFlightRequest;
import com.akinserhatgoksel.FlightSearchAPI.dto.converter.AirportDtoConverter;
import com.akinserhatgoksel.FlightSearchAPI.dto.converter.FlightDtoConverter;
import com.akinserhatgoksel.FlightSearchAPI.exception.FlightNotFoundException;
import com.akinserhatgoksel.FlightSearchAPI.model.Flight;
import com.akinserhatgoksel.FlightSearchAPI.repository.FlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightService {

    private final FlightRepository flightRepository;
    private final AirportService airportService;
    private final FlightDtoConverter converter;
    private final AirportDtoConverter airportDtoConverter;

    public List<FlightDto> getAllFlights() {
        return flightRepository.findAll()
                .stream()
                .map(converter::convertToDto)
                .collect(Collectors.toList());
    }

    public FlightDto getFlightById(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new FlightNotFoundException("Flight could not found by id: " + id));
        return converter.convertToDto(flight);
    }

    public FlightDto createFlight(CreateFlightRequest createFlightRequest) {
            Flight flight = new Flight();
            flight.setDepartureAirport(airportDtoConverter.convertToEntity(createFlightRequest.getDepartureAirport()));
            flight.setArrivalAirport(airportDtoConverter.convertToEntity(createFlightRequest.getArrivalAirport()));
            flight.setDepartureDateTime(createFlightRequest.getDepartureDateTime());
            flight.setReturnDateTime(createFlightRequest.getReturnDateTime());
            flight.setPrice(createFlightRequest.getPrice());

            Flight oneWayFlight = flightRepository.save(flight);

            if (flight.getReturnDateTime() != null) {
                Flight returnFlight = new Flight();
                returnFlight.setArrivalAirport(airportDtoConverter.convertToEntity(createFlightRequest.getDepartureAirport()));
                returnFlight.setDepartureAirport(airportDtoConverter.convertToEntity(createFlightRequest.getArrivalAirport()));
                returnFlight.setReturnDateTime(createFlightRequest.getReturnDateTime());
                returnFlight.setPrice(createFlightRequest.getPrice());

                Flight savedReturnFlight = flightRepository.save(returnFlight);

                List<Flight> roundTrips = new ArrayList<>();
                roundTrips.add(oneWayFlight);
                roundTrips.add(savedReturnFlight);
                flightRepository.saveAll(roundTrips);
            }

            return converter.convertToDto(oneWayFlight);
        }


    public FlightDto updateFlight(UpdateFlightRequest updateFlightRequest) {
        Flight existingFlight = flightRepository.findById(updateFlightRequest.getId()).orElseThrow(() -> new FlightNotFoundException("Flight could not found by id: " + updateFlightRequest.getId()));
        existingFlight.setDepartureAirport(airportDtoConverter.convertToEntity(updateFlightRequest.getDepartureAirport()));
        existingFlight.setArrivalAirport(airportDtoConverter.convertToEntity(updateFlightRequest.getArrivalAirport()));
        existingFlight.setDepartureDateTime(updateFlightRequest.getDepartureDateTime());
        existingFlight.setReturnDateTime(updateFlightRequest.getReturnDateTime());
        existingFlight.setPrice(updateFlightRequest.getPrice());

        Flight updatedFlight = flightRepository.save(existingFlight);
        return converter.convertToDto(updatedFlight);
    }

    public void deleteFlight(Long id) {
        if (flightRepository.existsById(id)) {
            flightRepository.deleteById(id);
        } else { throw new FlightNotFoundException("Flight could not found by id: " + id); }
    }

    public List<FlightDto> searchFlights(Long departureAirportId,
                                         Long arrivalAirportId,
                                         LocalDateTime departureDateTime,
                                         LocalDateTime returnDateTime) {
        AirportDto departureAirport = airportService.getAirportById(departureAirportId);
        AirportDto arrivalAirport = airportService.getAirportById(arrivalAirportId);


        if (returnDateTime != null) {
           List<FlightDto> departureInfo = flightRepository.
                    findByDepartureAirportAndArrivalAirportAndDepartureDateTimeAndReturnDateTime
                            (airportDtoConverter.convertToEntity(departureAirport),
                                    airportDtoConverter.convertToEntity(arrivalAirport),
                                    departureDateTime, returnDateTime)
                   .stream().map(converter::convertToDto).collect(Collectors.toList());

           List<FlightDto> returnInfo = flightRepository.findByDepartureAirportAndArrivalAirportAndReturnDateTime
                   (airportDtoConverter.convertToEntity(arrivalAirport),
                           airportDtoConverter.convertToEntity(departureAirport), returnDateTime)
                   .stream().map(converter::convertToDto).collect(Collectors.toList());

           List<FlightDto> mergeFlights = new ArrayList<>();
           mergeFlights.addAll(departureInfo);
           mergeFlights.addAll(returnInfo);
           return mergeFlights;
        } else {
            List<FlightDto> departureInfo = flightRepository.
                    findByDepartureAirportAndArrivalAirportAndDepartureDateTime
                            (airportDtoConverter.convertToEntity(departureAirport),
                                    airportDtoConverter.convertToEntity(arrivalAirport),
                                    departureDateTime)
                    .stream().map(converter::convertToDto).collect(Collectors.toList());

            return departureInfo;
        }
    }
    public List<FlightDto> findFlightsAfterDate(LocalDateTime startDate) {
        List<Flight> flightsAfterDate = flightRepository.findFlightsAfterDate(startDate);

        return flightsAfterDate.stream()
                .map(converter::convertToDto)
                .collect(Collectors.toList());
    }
}

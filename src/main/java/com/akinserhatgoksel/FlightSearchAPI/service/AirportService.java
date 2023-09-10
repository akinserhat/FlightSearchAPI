package com.akinserhatgoksel.FlightSearchAPI.service;

import com.akinserhatgoksel.FlightSearchAPI.dto.AirportDto;
import com.akinserhatgoksel.FlightSearchAPI.dto.converter.AirportDtoConverter;
import com.akinserhatgoksel.FlightSearchAPI.dto.request.CreateAirportRequest;
import com.akinserhatgoksel.FlightSearchAPI.dto.request.UpdateAirportRequest;
import com.akinserhatgoksel.FlightSearchAPI.exception.AirportNotFoundException;
import com.akinserhatgoksel.FlightSearchAPI.model.Airport;
import com.akinserhatgoksel.FlightSearchAPI.repository.AirportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirportService {

    private final AirportRepository airportRepository;
    private final AirportDtoConverter converter;

    public List<AirportDto> getAllAirports() {
        return airportRepository.findAll()
                .stream()
                .map(converter::convertToDto)
                .collect(Collectors.toList());
    }

    public AirportDto getAirportById(Long id) {
        Airport airport = airportRepository.findById(id)
                .orElseThrow(() -> new AirportNotFoundException("Airport could not found by id: " + id));
        return converter.convertToDto(airport);
    }

    public AirportDto createAirport(CreateAirportRequest createAirportRequest) {
        Airport airport = new Airport();
        airport.setCity(createAirportRequest.getCity());
        Airport createdAirport = airportRepository.save(airport);
        return converter.convertToDto(createdAirport);
    }

    public AirportDto updateAirport(UpdateAirportRequest updateAirportRequest) {
        //AirportNotFoundException!
        Airport airport = airportRepository.findById(updateAirportRequest.getId())
                .orElseThrow(() -> new AirportNotFoundException("Airport could not found by id: " + updateAirportRequest.getId()));
        airport.setId(updateAirportRequest.getId());
        airport.setCity(updateAirportRequest.getCity());

        Airport updatedAirport = airportRepository.save(airport);
        return converter.convertToDto(updatedAirport);
    }

    public void deleteAirport(Long id) {
        if (airportRepository.existsById(id)) {
            airportRepository.deleteById(id);
        } else {
            throw new AirportNotFoundException("Airport could not found by id: " + id);
        }
    }
}

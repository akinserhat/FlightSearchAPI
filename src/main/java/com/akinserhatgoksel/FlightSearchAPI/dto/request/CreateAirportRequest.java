package com.akinserhatgoksel.FlightSearchAPI.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAirportRequest {
    private String city;
}

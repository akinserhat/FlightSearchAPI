package com.akinserhatgoksel.FlightSearchAPI.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateAirportRequest {
    private Long id;
    private String city;
}

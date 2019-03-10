package com.example.demo.controller;

import com.example.demo.entity.Location;
import com.example.demo.service.LocationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("location/{deviceId}/add")
    Location addLocation(@PathVariable UUID deviceId, @RequestBody Location location) {
        return locationService.addLocation(deviceId, location);
    }

    @GetMapping("locations")
    List<Location> getLocations(@RequestParam("device") UUID deviceId,
                                @RequestParam("dateFrom") LocalDateTime dateFrom,
                                @RequestParam("dateTo") LocalDateTime dateTo) {
        return locationService.getLocations(deviceId,dateFrom,dateTo);
    }
}

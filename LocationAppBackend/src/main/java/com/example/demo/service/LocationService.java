package com.example.demo.service;

import com.example.demo.entity.Device;
import com.example.demo.entity.Location;
import com.example.demo.repositories.DeviceRepository;
import com.example.demo.repositories.LocationRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class LocationService {

    private final LocationRepository locationRepository;
    private final DeviceRepository deviceRepository;

    public LocationService(LocationRepository locationRepository, DeviceRepository deviceRepository) {
        this.locationRepository = locationRepository;
        this.deviceRepository = deviceRepository;
    }

    public Location addLocation(UUID deviceId, Location location) {
        final Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new EntityNotFoundException("Device didn't exist"));

        location.setDevice(device);
        return locationRepository.save(location);
    }

    public List<Location> getLocations(UUID deviceId, LocalDateTime dateFrom, LocalDateTime dateTo) {
        final Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new EntityNotFoundException("Device didn't exist"));

        return locationRepository.findByDeviceAndDateTimeAfterAndDateTimeBefore(device, dateFrom, dateTo);
    }
}

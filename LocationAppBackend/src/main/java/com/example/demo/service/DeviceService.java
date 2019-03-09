package com.example.demo.service;

import com.example.demo.entity.Device;
import com.example.demo.repositories.DeviceRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(final DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public Device login(final Device device) {
        return deviceRepository.findByLoginAndPassword(device.getLogin(), device.getPassword())
                .orElseThrow(()-> new EntityNotFoundException("User didn't exist"));
    }
}

package com.example.demo.service;

import com.example.demo.entity.Device;
import com.example.demo.repositories.DeviceRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public DeviceService(final DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public Device login(final Device device) {
        return deviceRepository.findByLoginAndPassword(device.getLogin(), device.getPassword())
                .orElseThrow(() -> new EntityNotFoundException("User didn't exist"));
    }

    public Device register(final Device device) {
        if (deviceRepository.findByLogin(device.getLogin()).isPresent()) {
            throw new EntityNotFoundException("User already exist");
        }
        return deviceRepository.save(device);
    }

    public List<Device> getAll() {
        return deviceRepository.findAll();
    }

    public Device getOne(UUID id) {
        return deviceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Device didn't exist"));
    }
}

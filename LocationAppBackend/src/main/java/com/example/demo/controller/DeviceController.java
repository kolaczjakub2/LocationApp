package com.example.demo.controller;

import com.example.demo.entity.Device;
import com.example.demo.service.DeviceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @PostMapping("login")
    Device login(@RequestBody Device device){
        return deviceService.login(device);
    }

    @PostMapping("register")
    Device register(@RequestBody Device device){
        return deviceService.register(device);
    }

    @GetMapping("all")
    List<Device> getAll(){
        return deviceService.getAll();
    }

    @GetMapping("{id}")
    Device getOne(@PathVariable UUID id){
        return deviceService.getOne(id);
    }

}

package com.example.demo.controller;

import com.example.demo.entity.Device;
import com.example.demo.service.DeviceService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}

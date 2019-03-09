package com.example.demo.repositories;

import com.example.demo.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<Device, UUID> {
    Optional<Device> findByLoginAndPassword(final String login, final String password);
    Optional<Device> findByLogin(final String login);
}

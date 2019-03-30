package com.example.demo.repositories;

import com.example.demo.entity.Device;
import com.example.demo.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {
    List<Location> findByDeviceAndDateTimeAfterAndDateTimeBefore(final Device device, final LocalDateTime dateFrom, final LocalDateTime dateTo);
}

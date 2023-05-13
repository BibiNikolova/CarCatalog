package com.example.carcatalog.repository;

import com.example.carcatalog.model.entity.Transmission;
import com.example.carcatalog.model.enums.TransmissionName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransmissionRepo extends JpaRepository<Transmission, Long> {

    Optional<Transmission> findByTransmissionName(TransmissionName transmissionName);
}

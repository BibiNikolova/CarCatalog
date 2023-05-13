package com.example.carcatalog.repository;

import com.example.carcatalog.model.entity.FuelType;
import com.example.carcatalog.model.enums.FuelTypeName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuelTypeRepo extends JpaRepository<FuelType, Long> {

    Optional<FuelType> findByFuelTypeName(FuelTypeName fuelTypeName);
}

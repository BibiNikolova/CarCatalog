package com.example.carcatalog.repository;

import com.example.carcatalog.model.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepo extends JpaRepository<Car, Long>, JpaSpecificationExecutor<Car> {

    Optional<Car> findByVinNumber(String vinNum);
}

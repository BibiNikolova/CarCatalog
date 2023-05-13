package com.example.carcatalog.repository;

import com.example.carcatalog.model.entity.Car;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepo extends JpaRepository<Car, Long> {

    @Query("SELECT c FROM Car c WHERE CONCAT(c.fuelType, ' ', c.registrationDate, ' ', c.model, ' ', c.model.brand, ' ', c.transmission, ' ', c.price) LIKE %?1%")
    Optional<Car> searchCarSQL(String query);

}

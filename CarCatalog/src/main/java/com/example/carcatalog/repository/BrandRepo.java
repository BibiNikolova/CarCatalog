package com.example.carcatalog.repository;

import com.example.carcatalog.model.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepo extends JpaRepository<Brand, Long> {
    Optional<Brand> findByBrandName(String name);
}

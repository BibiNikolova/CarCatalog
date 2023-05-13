package com.example.carcatalog.repository;

import com.example.carcatalog.model.entity.Brand;
import com.example.carcatalog.model.enums.BrandName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepo extends JpaRepository<Brand, Long> {
    Brand findByBrandName(BrandName name);
}

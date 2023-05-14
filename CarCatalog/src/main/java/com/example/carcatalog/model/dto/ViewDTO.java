package com.example.carcatalog.model.dto;

import com.example.carcatalog.model.entity.Brand;
import com.example.carcatalog.model.entity.FuelType;
import com.example.carcatalog.model.entity.Model;
import com.example.carcatalog.model.entity.Transmission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViewDTO {

    private Long id;
    private String vinNumber;
    private Brand brand;
    private Model model;
    private BigDecimal price;
    private LocalDate registrationDate;
    private Transmission transmission;
    private FuelType fuelType;
    private String remarks;
}

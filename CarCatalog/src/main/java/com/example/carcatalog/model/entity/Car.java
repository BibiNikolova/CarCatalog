package com.example.carcatalog.model.entity;

import jakarta.persistence.*;
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
@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="vin_num", nullable = false)
    private String vinNumber;
    @ManyToOne
    private Model model;
    @Column(nullable = false)
    private BigDecimal price;
    @Column(name= "reg_date", nullable = false)
    private LocalDate registrationDate;
    @ManyToOne
    private Transmission transmission;
    @ManyToOne
    private FuelType fuelType;
    @Column
    private String remarks;


}


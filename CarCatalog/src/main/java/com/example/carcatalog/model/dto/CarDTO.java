package com.example.carcatalog.model.dto;

import com.example.carcatalog.model.enums.FuelTypeName;
import com.example.carcatalog.model.enums.TransmissionName;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarDTO {

    @Size(min = 17, max = 17, message = "VIN Number should be 17 characters long.")
    @NotBlank
    private String vinNumber;
    @NotNull(message = "You should select a model.")
    private String modelName;
    @Positive(message = "Price should be positive.")
    private BigDecimal price;
    @Past(message = "Registration date should be in the past.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate registrationDate;
    @NotNull(message = "You should select a transmission!")
    private TransmissionName transmissionName;
    @NotNull(message = "You should select a fuel type!")
    private FuelTypeName fuelTypeName;
    private String remarks;


}

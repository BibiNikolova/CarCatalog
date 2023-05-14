package com.example.carcatalog.model.dto;

import com.example.carcatalog.model.enums.BrandName;
import com.example.carcatalog.model.enums.FuelTypeName;
import com.example.carcatalog.model.enums.ModelName;
import com.example.carcatalog.model.enums.TransmissionName;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
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
public class SearchDTO {
    private ModelName modelName;
    private BrandName brandName;
    @Positive(message = "Price should be positive.")
    private BigDecimal price;
    @Past(message = "Registration date should be in the past.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate registrationDate;
    private TransmissionName transmissionName;
    private FuelTypeName fuelTypeName;

}

package com.example.carcatalog.seeders;

import com.example.carcatalog.model.entity.Brand;
import com.example.carcatalog.model.entity.FuelType;
import com.example.carcatalog.model.entity.Model;
import com.example.carcatalog.model.entity.Transmission;
import com.example.carcatalog.model.enums.FuelTypeName;
import com.example.carcatalog.model.enums.TransmissionName;
import com.example.carcatalog.repository.BrandRepo;
import com.example.carcatalog.repository.FuelTypeRepo;
import com.example.carcatalog.repository.ModelRepo;
import com.example.carcatalog.repository.TransmissionRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;

@Component
public class DbSeeder implements CommandLineRunner {

    private final BrandRepo brandRepo;
    private final FuelTypeRepo fuelTypeRepo;
    private final ModelRepo modelRepo;
    private final TransmissionRepo transmissionRepo;

    public DbSeeder(BrandRepo brandRepo, FuelTypeRepo fuelTypeRepo, ModelRepo modelRepo, TransmissionRepo transmissionRepo) {
        this.brandRepo = brandRepo;
        this.fuelTypeRepo = fuelTypeRepo;
        this.modelRepo = modelRepo;
        this.transmissionRepo = transmissionRepo;
    }

    @Override
    public void run(String... args) {

        initBrand();

        initModel();

        initFuelType();

        initTransmission();


    }

    private void initBrand() {
        if (brandRepo.count() == 0) {

            Set<String> brands = Set.of("BMW", "AUDI", "VW");
                    brands.forEach(b -> {
                        Brand brand = new Brand();
                        brand.setBrandName(b);
                        this.brandRepo.save(brand);
                    });
        }
    }

    private void initTransmission() {
        if (transmissionRepo.count() == 0) {

            Arrays.stream(TransmissionName.values())
                    .forEach(t -> {
                        Transmission transmission = new Transmission();
                        transmission.setTransmissionName(t);
                        this.transmissionRepo.save(transmission);
                    });
        }
    }

    private void initModel() {
        if (modelRepo.count() == 0) {

            Set<String> models = Set.of("X4", "Q5", "Q7");
                   models .forEach(m -> {
                        Model model = new Model();
                        model.setModelName(m);
                        switch (m){
                            case "X4" -> model.setBrand(brandRepo.findByBrandName("BMW").orElseThrow());
                            case "Q7" -> model.setBrand(brandRepo.findByBrandName("AUDI").orElseThrow());
                            case "TOUAREG"-> model.setBrand(brandRepo.findByBrandName("VW").orElseThrow());
                        }
                        this.modelRepo.save(model);
                    });
        }
    }

    private void initFuelType() {
        if (fuelTypeRepo.count() == 0) {

            Arrays.stream(FuelTypeName.values())
                    .forEach(f -> {
                        FuelType fuelType = new FuelType();
                        fuelType.setFuelTypeName(f);
                        this.fuelTypeRepo.save(fuelType);
                    });
        }
    }


}

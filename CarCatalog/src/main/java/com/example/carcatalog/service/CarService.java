package com.example.carcatalog.service;

import com.example.carcatalog.exception.CarNotFoundException;
import com.example.carcatalog.model.dto.CreateUpdateCarDTO;
import com.example.carcatalog.model.entity.Car;
import com.example.carcatalog.model.entity.FuelType;
import com.example.carcatalog.model.entity.Model;
import com.example.carcatalog.model.entity.Transmission;
import com.example.carcatalog.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final BrandRepo brandRepo;
    private final FuelTypeRepo fuelTypeRepo;
    private final ModelRepo modelRepo;
    private final TransmissionRepo transmissionRepo;
    private final CarRepo carRepo;

    public CarService(BrandRepo brandRepo, FuelTypeRepo fuelTypeRepo, ModelRepo modelRepo, TransmissionRepo transmissionRepo, CarRepo carRepo) {
        this.brandRepo = brandRepo;
        this.fuelTypeRepo = fuelTypeRepo;
        this.modelRepo = modelRepo;
        this.transmissionRepo = transmissionRepo;
        this.carRepo = carRepo;
    }

    public List<CreateUpdateCarDTO> getAllCars() {
        return carRepo.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    private CreateUpdateCarDTO map(Car car) {

        return CreateUpdateCarDTO.builder()
                .id(car.getId())
                .modelName(car.getModel().getModelName())
                .remarks(car.getRemarks())
                .price(car.getPrice())
                .fuelTypeName(car.getFuelType().getFuelTypeName())
                .registrationDate(car.getRegistrationDate())
                .transmissionName(car.getTransmission().getTransmissionName())
                .build();
    }

    public long createCar(CreateUpdateCarDTO newCar) {

        Car car = Car.builder()
                .vinNumber(newCar.getVinNumber())
                .model(getModelName(newCar))
                .price(newCar.getPrice())
                .registrationDate(newCar.getRegistrationDate())
                .transmission(getTransmissionName(newCar))
                .fuelType(getFuelTypeName(newCar))
                .remarks(newCar.getRemarks())
                .build();

        return this.carRepo.save(car).getId();
    }

    private FuelType getFuelTypeName(CreateUpdateCarDTO car) {
        return this.fuelTypeRepo.findByFuelTypeName(car.getFuelTypeName()).orElseThrow();
    }

    private Transmission getTransmissionName(CreateUpdateCarDTO car) {
        return this.transmissionRepo.findByTransmissionName(car.getTransmissionName()).orElseThrow();
    }

    private Model getModelName(CreateUpdateCarDTO car) {
        return this.modelRepo.findByModelName(car.getModelName()).orElseThrow();
    }

    public CreateUpdateCarDTO updateCar(Long id, CreateUpdateCarDTO updatedCar) {
        Car car = carRepo.findById(id).orElseThrow(() -> new CarNotFoundException(id));
        car.setVinNumber(updatedCar.getVinNumber());
        car.setModel(getModelName(updatedCar));
        car.setPrice(updatedCar.getPrice());
        car.setRegistrationDate(updatedCar.getRegistrationDate());
        car.setTransmission(getTransmissionName(updatedCar));
        car.setFuelType(getFuelTypeName(updatedCar));
        car.setRemarks(updatedCar.getRemarks());

        this.carRepo.save(car);

        return carRepo.findById(id)
                .map(this::map).orElseThrow(() -> new CarNotFoundException(id));
    }

    public void deleteById(Long id) {
        carRepo.deleteById(id);
    }

    public List<CreateUpdateCarDTO> searchCars(String query) {
        return carRepo.searchCarsSQL(query)
                .stream()
                .map(this::map)
                .toList();
    }

}

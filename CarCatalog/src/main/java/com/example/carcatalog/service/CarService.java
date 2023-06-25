package com.example.carcatalog.service;

import com.example.carcatalog.exception.CarNotFoundException;
import com.example.carcatalog.model.dto.CarDTO;
import com.example.carcatalog.model.dto.SearchDTO;
import com.example.carcatalog.model.dto.ViewDTO;
import com.example.carcatalog.model.entity.Car;
import com.example.carcatalog.model.entity.FuelType;
import com.example.carcatalog.model.entity.Model;
import com.example.carcatalog.model.entity.Transmission;
import com.example.carcatalog.repository.CarRepo;
import com.example.carcatalog.repository.FuelTypeRepo;
import com.example.carcatalog.repository.ModelRepo;
import com.example.carcatalog.repository.TransmissionRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class CarService {
    private final FuelTypeRepo fuelTypeRepo;
    private final ModelRepo modelRepo;
    private final TransmissionRepo transmissionRepo;
    private final CarRepo carRepo;

    public CarService(FuelTypeRepo fuelTypeRepo, ModelRepo modelRepo, TransmissionRepo transmissionRepo, CarRepo carRepo) {
        this.fuelTypeRepo = fuelTypeRepo;
        this.modelRepo = modelRepo;
        this.transmissionRepo = transmissionRepo;
        this.carRepo = carRepo;
    }

    public List<ViewDTO> getAll() {
        return carRepo.findAll()
                .stream()
                .sorted((f, s) -> s.getPrice().compareTo(f.getPrice()))
                .map(this::map)
                .toList();
    }

    public long create(CarDTO newCar) {

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

    public ViewDTO update(Long id, CarDTO updatedCar) {
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

    private ViewDTO map(Car car) {

        return ViewDTO.builder()
                .id(car.getId())
                .brand(car.getModel().getBrand())
                .model(car.getModel())
                .remarks(car.getRemarks())
                .price(car.getPrice())
                .fuelType(car.getFuelType())
                .registrationDate(car.getRegistrationDate())
                .transmission(car.getTransmission())
                .build();
    }

    private FuelType getFuelTypeName(CarDTO car) {
        return this.fuelTypeRepo.findByFuelTypeName(car.getFuelTypeName()).orElseThrow();
    }

    private Transmission getTransmissionName(CarDTO car) {
        return this.transmissionRepo.findByTransmissionName(car.getTransmissionName()).orElseThrow();
    }

    private Model getModelName(CarDTO car) {
        return this.modelRepo.findByModelName(car.getModelName()).orElseThrow();
    }

    public List<ViewDTO> getSearch(SearchDTO searched) {

        Stream<Car> stream = this.carRepo.findAll().stream();

        if (searched.getModelName() != null) {
            stream = stream.filter(car -> car.getModel().getModelName().equals(searched.getModelName()));
        }
        if (searched.getRegistrationDate() != null) {
            stream = stream.filter(car -> car.getRegistrationDate().isAfter(searched.getRegistrationDate()));
        }
        if (searched.getTransmissionName() != null) {
            stream = stream.filter(car -> car.getTransmission().getTransmissionName().equals(searched.getTransmissionName()));
        }
        if (searched.getPrice() != null) {
            stream = stream.filter(car -> car.getPrice().compareTo(searched.getPrice()) < 0);
        }
        if (searched.getFuelTypeName() != null) {
            stream = stream.filter(car -> car.getFuelType().getFuelTypeName().equals(searched.getFuelTypeName()));
        }
        if (searched.getBrandName() != null) {
            stream = stream.filter(car -> car.getModel().getBrand().getBrandName().equals(searched.getBrandName()));
        }
        return stream
                .map(this::map)
                .sorted((f, s) -> s.getPrice().compareTo(f.getPrice()))
                .toList();
    }

}

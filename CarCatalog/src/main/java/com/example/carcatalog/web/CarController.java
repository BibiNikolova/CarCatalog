package com.example.carcatalog.web;

import com.example.carcatalog.model.dto.CarSearchDTO;
import com.example.carcatalog.model.dto.CreateUpdateCarDTO;
import com.example.carcatalog.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<CreateUpdateCarDTO>> getAllCars() {

        return ResponseEntity.ok(carService.getAllCars());
    }

    @PostMapping
    public ResponseEntity<CreateUpdateCarDTO> createCar(@RequestBody CreateUpdateCarDTO newCar,
                                                        UriComponentsBuilder uriComponentsBuilder) {

        long newCarId = carService.createCar(newCar);

        return ResponseEntity
                .created(uriComponentsBuilder.path("/api/cars/{id}").build(newCarId))
                .build();

    }
    @PutMapping("/{id}")
    public ResponseEntity<CreateUpdateCarDTO> updateCar(@PathVariable("id") Long id,
                                                        @RequestBody CreateUpdateCarDTO updatedCar) {

       return ResponseEntity.ok(carService.updateCar(id, updatedCar));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CarSearchDTO> deleteCar(@PathVariable("id") Long id) {//TODO: choose DTO

        carService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<CreateUpdateCarDTO>> searchProducts(@RequestParam("query") String query){
        return ResponseEntity.ok(carService.searchCars(query));
    }
}

package com.example.carcatalog.web;

import com.example.carcatalog.model.dto.CarDTO;
import com.example.carcatalog.model.dto.SearchDTO;
import com.example.carcatalog.model.dto.ViewDTO;
import com.example.carcatalog.service.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
    public ResponseEntity<List<ViewDTO>> getAll() {

        return ResponseEntity.ok(carService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<ViewDTO> create(@RequestBody @Validated CarDTO newCar,
                                         UriComponentsBuilder uriComponentsBuilder) {

        long newCarId = carService.create(newCar);

        return ResponseEntity
                .created(uriComponentsBuilder.path("/api/cars/add/{id}").build(newCarId))
                .build();

    }
    @PutMapping("/{id}")
    public ResponseEntity<ViewDTO> update(@PathVariable("id") Long id,
                                            @RequestBody @Validated CarDTO updatedCar) {

       return ResponseEntity.ok(carService.update(id, updatedCar));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ViewDTO> delete(@PathVariable("id") Long id) {//TODO: choose DTO

        carService.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search")
    public ResponseEntity<List<ViewDTO>> search(@RequestBody @Validated SearchDTO searchDTO){
        return ResponseEntity.ok(carService.getSearch(searchDTO));
    }
}

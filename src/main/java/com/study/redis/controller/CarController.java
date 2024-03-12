package com.study.redis.controller;

import com.study.redis.dto.CarDto;
import com.study.redis.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/car")
@AllArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<CarDto> create(@RequestBody CarDto dto) {
        return ResponseEntity.ok(this.carService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(this.carService.get(id));
    }
}

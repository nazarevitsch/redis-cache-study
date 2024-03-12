package com.study.redis.service;

import com.study.redis.converter.CarConverter;
import com.study.redis.dto.CarDto;
import com.study.redis.model.Car;
import com.study.redis.repository.CarRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final CarConverter carConverter;

    @CachePut(value = "car", key = "#result.id")
    public CarDto create(CarDto dto) {
        Car car = this.carConverter.fromDtoToDb(dto);
        Car savedCar = this.carRepository.save(car);
        return this.carConverter.fromDbToDto(savedCar);
    }

    @Cacheable(value = "car", key = "#id")
    public CarDto get(Long id) {
        return this.carRepository.findById(id)
                .map(this.carConverter::fromDbToDto)
                .orElseGet(CarDto::new);
    }
}

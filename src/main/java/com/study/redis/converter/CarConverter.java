package com.study.redis.converter;

import com.study.redis.dto.CarDto;
import com.study.redis.model.Car;
import org.springframework.stereotype.Component;

@Component
public class CarConverter {

    public CarDto fromDbToDto(Car car) {
        CarDto dto = new CarDto();

        dto.setId(car.getId());
        dto.setBrand(car.getBrand());

        return dto;
    }

    public Car fromDtoToDb(CarDto dto) {
        Car car = new Car();

        car.setId(dto.getId());
        car.setBrand(dto.getBrand());

        return car;
    }
}

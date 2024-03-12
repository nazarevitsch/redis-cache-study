package com.study.redis.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class CarDto implements Serializable {

    private Long id;
    private String brand;
}

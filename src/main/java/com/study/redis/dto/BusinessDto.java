package com.study.redis.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class BusinessDto implements Serializable {

    private Long id;
    private String name;
}

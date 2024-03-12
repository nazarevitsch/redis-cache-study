package com.study.redis.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PersonDto implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
}

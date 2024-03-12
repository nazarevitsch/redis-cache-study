package com.study.redis.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "business")
public class Business {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}

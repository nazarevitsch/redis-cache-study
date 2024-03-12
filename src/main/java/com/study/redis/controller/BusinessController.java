package com.study.redis.controller;

import com.study.redis.dto.BusinessDto;
import com.study.redis.service.BusinessService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/business")
@AllArgsConstructor
public class BusinessController {

    private final BusinessService businessService;

    @PostMapping
    public ResponseEntity<BusinessDto> create(@RequestBody BusinessDto dto) {
        return ResponseEntity.ok(this.businessService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusinessDto> get(@PathVariable Long id) {
        return ResponseEntity.ok(this.businessService.get(id));
    }

    @GetMapping("/{ids}")
    public ResponseEntity<List<BusinessDto>> getByIds(@PathVariable List<Long> ids) {
        return ResponseEntity.ok(this.businessService.getByIds(ids));
    }
}

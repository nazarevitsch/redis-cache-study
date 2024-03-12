package com.study.redis.service;

import com.study.redis.converter.BusinessConverter;
import com.study.redis.dto.BusinessDto;
import com.study.redis.model.Business;
import com.study.redis.repository.BusinessRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BusinessService {

    private final BusinessRepository businessRepository;
    private final BusinessConverter businessConverter;

    @CachePut(value = "business", key = "#result.id")
    public BusinessDto create(BusinessDto dto) {
        Business business = this.businessConverter.fromDtoToDb(dto);
        Business savedBusiness = this.businessRepository.save(business);
        return this.businessConverter.fromDbToDto(savedBusiness);
    }

    @Cacheable(value = "business", key = "#id")
    public BusinessDto get(Long id) {
        return this.businessRepository.findById(id)
                .map(this.businessConverter::fromDbToDto)
                .orElseGet(BusinessDto::new);
    }

    public List<BusinessDto> getByIds(List<Long> ids) {
        return this.businessRepository.findAllById(ids)
                .stream()
                .map(this.businessConverter::fromDbToDto)
                .toList();
    }
}

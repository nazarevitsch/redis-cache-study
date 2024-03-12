package com.study.redis.converter;

import com.study.redis.dto.BusinessDto;
import com.study.redis.model.Business;
import org.springframework.stereotype.Component;

@Component
public class BusinessConverter {

    public BusinessDto fromDbToDto(Business business) {
        BusinessDto dto = new BusinessDto();

        dto.setId(business.getId());
        dto.setName(business.getName());

        return dto;
    }

    public Business fromDtoToDb(BusinessDto dto) {
        Business business = new Business();

        business.setId(dto.getId());
        business.setName(dto.getName());

        return business;
    }
}

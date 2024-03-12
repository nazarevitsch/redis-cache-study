package com.study.redis.service;

import com.study.redis.converter.PersonConverter;
import com.study.redis.dto.PersonDto;
import com.study.redis.model.Person;
import com.study.redis.repository.PersonRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;
    private final PersonConverter personConverter;

    @CachePut(value = "person", key = "#result.id")
    public PersonDto create(PersonDto dto) {
        Person person = this.personConverter.fromDtoToDb(dto);
        Person savedPerson = this.personRepository.save(person);
        return this.personConverter.fromDbToDto(savedPerson);
    }

    @Cacheable(value = "person", key = "#id")
    public PersonDto get(Long id) {
        return this.personRepository.findById(id)
                .map(this.personConverter::fromDbToDto)
                .orElseGet(PersonDto::new);
    }
}

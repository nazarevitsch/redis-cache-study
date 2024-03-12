package com.study.redis.converter;

import com.study.redis.dto.PersonDto;
import com.study.redis.model.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonConverter {

    public PersonDto fromDbToDto(Person person) {
        PersonDto dto = new PersonDto();

        dto.setId(person.getId());
        dto.setFirstName(person.getFirstName());
        dto.setLastName(person.getLastName());
        dto.setAge(person.getAge());

        return dto;
    }

    public Person fromDtoToDb(PersonDto dto) {
        Person person = new Person();

        person.setId(dto.getId());
        person.setFirstName(dto.getFirstName());
        person.setLastName(dto.getLastName());
        person.setAge(dto.getAge());

        return person;
    }
}

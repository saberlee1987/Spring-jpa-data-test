package com.saber.spring_data_jpa.services;

import com.saber.spring_data_jpa.model.Person;
import com.saber.spring_data_jpa.model.PersonDto;

import java.util.List;

public interface PersonService {
    List<Person> findAll();
    Person findByNationalCode(String nationalCode);
    Person save(PersonDto personDto);
    Person update(PersonDto personDto);
    Boolean delete(Long id);
    Person findById(Long id);
}

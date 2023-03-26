package com.saber.spring_data_jpa.services;

import com.saber.spring_data_jpa.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    List<Person> findAll();
    Person findByNationalCode(String nationalCode);
}

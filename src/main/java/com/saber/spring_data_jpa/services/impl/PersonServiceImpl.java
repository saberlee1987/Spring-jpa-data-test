package com.saber.spring_data_jpa.services.impl;

import com.saber.spring_data_jpa.exceptions.ResourceNotFoundException;
import com.saber.spring_data_jpa.model.Person;
import com.saber.spring_data_jpa.repositories.PersonRepository;
import com.saber.spring_data_jpa.services.PersonService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Override
    public Person findByNationalCode(String nationalCode) {
        Optional<Person> optionalPerson = personRepository.findByNationalCode(nationalCode);
        if (optionalPerson.isEmpty()) {
            throw new ResourceNotFoundException(String.format("person for nationalCode %s does not exist", nationalCode));
        }
        return optionalPerson.get();
    }
}

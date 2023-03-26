package com.saber.spring_data_jpa.services.impl;

import com.saber.spring_data_jpa.exceptions.ResourceDuplicationException;
import com.saber.spring_data_jpa.exceptions.ResourceNotFoundException;
import com.saber.spring_data_jpa.model.Person;
import com.saber.spring_data_jpa.model.PersonDto;
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

    @Override
    public Person save(PersonDto personDto) {
        String nationalCode = personDto.getNationalCode();
        checkPersonDuplicateByNationalCode(nationalCode);
        Person person = getPersonFromDto(personDto, new Person());
        return personRepository.save(person);
    }

    @Override
    public Person update(PersonDto personDto) {
        Person oldPerson = findById(personDto.getId());
        if (!oldPerson.getNationalCode().equals(personDto.getNationalCode())) {
            checkPersonDuplicateByNationalCode(personDto.getNationalCode());
        }
        Person personUpdated = getPersonFromDto(personDto, oldPerson);
        return personRepository.save(personUpdated);
    }

    @Override
    public Boolean delete(Long id) {
        Person oldPerson = findById(id);
        personRepository.deleteById(oldPerson.getId());
        return true;
    }

    public Person findById(Long id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isEmpty()) {
            throw new ResourceNotFoundException(String.format("person for id %s does not exist", id));
        }
        return optionalPerson.get();
    }

    private void checkPersonDuplicateByNationalCode(String nationalCode) {
        Optional<Person> optionalPerson = personRepository.findByNationalCode(nationalCode);
        if (optionalPerson.isPresent()) {
            throw new ResourceDuplicationException(String.format("person for nationalCode %s already exist", nationalCode));
        }
    }

    private Person getPersonFromDto(PersonDto personDto, Person person) {
        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());
        person.setAge(personDto.getAge());
        person.setEmail(personDto.getEmail());
        person.setNationalCode(personDto.getNationalCode());
        person.setMobile(personDto.getMobile());
        return person;
    }
}

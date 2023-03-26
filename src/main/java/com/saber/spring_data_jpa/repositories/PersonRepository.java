package com.saber.spring_data_jpa.repositories;

import com.saber.spring_data_jpa.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PersonRepository  extends JpaRepository<Person,Long> {
    Optional<Person> findByNationalCode(String nationalCode);
}

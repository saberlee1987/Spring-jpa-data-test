package com.saber.spring_data_jpa.main;

import com.saber.spring_data_jpa.config.AppConfig;
import com.saber.spring_data_jpa.model.Person;
import com.saber.spring_data_jpa.model.PersonDto;
import com.saber.spring_data_jpa.model.SimplePerson;
import com.saber.spring_data_jpa.services.PersonService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        PersonService personService = context.getBean(PersonService.class);

        //PersonDto personDto=new PersonDto();
//        personDto.setFirstName("john2");
//        personDto.setLastName("Rambo2");
        //personDto.setNationalCode("4122587436");
//        personDto.setAge(70);
//        personDto.setEmail("johnRambo@yahoo.com");
//        personDto.setMobile("09017856354");
        //personService.save(personDto);
        //Long id = personService.findByNationalCode(personDto.getNationalCode()).getId();
//        personDto.setId(id);
//        personService.update(personDto);
        //personService.delete(id);

        SimplePerson simplePerson = personService.findSimplePersonByNationalCode("0079028748");

        String s= String.format("{\"firstName\":\"%s\",\"lastName\":\"%s\",\"nationalCode\":\"%s\"}", simplePerson.getFirstName(),simplePerson.getLastName(),simplePerson.getNationalCode());
        System.out.println("simple Person  ===> "+s);


        List<Person> personList = personService.findAll();

        personList.forEach(person -> {
            System.out.println(person);
            System.out.println("================================================================================================");
        });
    }
}

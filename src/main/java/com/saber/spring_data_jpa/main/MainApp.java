package com.saber.spring_data_jpa.main;

import com.saber.spring_data_jpa.config.AppConfig;
import com.saber.spring_data_jpa.model.Person;
import com.saber.spring_data_jpa.services.PersonService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        PersonService personService = context.getBean(PersonService.class);
        List<Person> personList = personService.findAll();

        personList.forEach(person -> {
            System.out.println(person);
            System.out.println("================================================================================================");
        });
    }
}

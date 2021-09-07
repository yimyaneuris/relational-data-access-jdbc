package com.relational.dataaccessjdbc;

import com.relational.dataaccessjdbc.model.Ciudad;
import com.relational.dataaccessjdbc.model.Person;
import com.relational.dataaccessjdbc.repository.PersonRepository;
import com.relational.dataaccessjdbc.util.DataBaseSeeder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

@SpringBootApplication
@RequiredArgsConstructor
@Slf4j
public class RelationalDataAccessJdbcApplication implements CommandLineRunner {

    final JdbcTemplate jdbcTemplate;
    final PersonRepository personRepository;
    final DataBaseSeeder dataBaseSeeder;

    public static void main(String[] args) {
        SpringApplication.run(RelationalDataAccessJdbcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("Insertando Data en la base de datos");
        dataBaseSeeder.insertData();

        log.info("@@ Find all");
        personRepository.findAll().forEach(System.out::println);

        log.info("@@ Find by Id");
        Optional<Person> person = personRepository.findById(1L);
        person.ifPresent(System.out::println);

        log.info("@@ Save");
        Person p = new Person("Gonze","Carrasco");
        Person result = personRepository.save(p);
        log.info(result.toString());

        log.info("@@ Delete");
        // first you need find the resource and delete after.
        person.ifPresent(personRepository::delete);

        log.info("@@ Find All");
        personRepository.findAll().forEach(System.out::println);

        log.info("Find by first name");
        personRepository.findByFirstName("Julissa").forEach(System.out::println);

        log.info("Update first name by Id");
        personRepository.updatePerson(2L, "Persona Actualizada");

        log.info("Find All");
        personRepository.findAll().forEach(System.out::println);


        log.info("@@ calling a function");
        personRepository.calculateIncome(4000).ifPresent(System.out::println);
    }
}

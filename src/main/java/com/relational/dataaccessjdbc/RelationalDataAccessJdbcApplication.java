package com.relational.dataaccessjdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class RelationalDataAccessJdbcApplication implements CommandLineRunner {

    final JdbcTemplate jdbcTemplate;
    private static final Logger logger = LoggerFactory.getLogger(RelationalDataAccessJdbcApplication.class);

    @Autowired
    public RelationalDataAccessJdbcApplication(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static void main(String[] args) {
        SpringApplication.run(RelationalDataAccessJdbcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        logger.debug("Creating Tables");

        jdbcTemplate.execute("DROP TABLE customers IF EXISTS ");
        jdbcTemplate.execute("CREATE TABLE customers(id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))");

        // Split up the array of whole names into an array of first/last names
        List<Object[]> list = Arrays.asList("Jose Mendez", "Miguel Tiburcio", "Pedro Hernandez", "Maria Josefa")
                .stream()
                .map(name -> name.split(" "))
                .collect(Collectors.toList());


        list.forEach(name -> logger.info(String.format("Inserting customer record for %s %s", name[0], name[1])));

        // Uses JdbcTemplate's batchUpdate operation to bulk load data
        jdbcTemplate.batchUpdate("INSERT INTO customers (first_name, last_name) VALUES (?,?)", list);

        logger.info("Querying for customer records where first_name = 'Josh':");

        jdbcTemplate.query("SELECT * FROM customers WHERE first_name = ?",
                new Object [] {"Jose"},
                (rs, row) ->
                    new Customer(rs.getLong("id"),
                            rs.getString("first_name"),
                            rs.getString("last_name"))
                ).forEach(customer -> logger.info(customer.toString()));
    }
}

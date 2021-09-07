package com.relational.dataaccessjdbc.util;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataBaseSeeder {

    private final JdbcTemplate jdbcTemplate;

    public void insertData() {
        jdbcTemplate.execute("DROP FUNCTION IF EXISTS  CalcIncome");
        jdbcTemplate.execute("CREATE FUNCTION CalcIncome ( starting_value INT )\n" +
                "RETURNS INT\n" +
                "\n" +
                "BEGIN\n" +
                "\n" +
                "   DECLARE income INT;\n" +
                "\n" +
                "   SET income = 10;\n" +
                "\n" +
                "   WHILE income < 200 DO\n" +
                "     SET income = income + starting_value;\n" +
                "   END WHILE;\n" +
                "\n" +
                "   RETURN income;\n" +
                "\n" +
                "END; ");
        jdbcTemplate.update("truncate table persons");
        jdbcTemplate.update("insert into persons(first_name, last_name) values ('Andres', 'medina')");
        jdbcTemplate.update("insert into persons(first_name, last_name) values ('Clara', 'Amparo')");
        jdbcTemplate.update("insert into persons(first_name, last_name) values ('Julissa', 'Arias')");
        jdbcTemplate.update("insert into persons(first_name, last_name) values ('Miguel', 'Hernandez')");
        jdbcTemplate.update("insert into persons(first_name, last_name) values ('Jose', 'Pena')");
        jdbcTemplate.update("insert into persons(first_name, last_name) values ('Jorge', 'Mendez')");
    }
}

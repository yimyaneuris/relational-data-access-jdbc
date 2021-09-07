package com.relational.dataaccessjdbc.repository;

import com.relational.dataaccessjdbc.model.Person;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

    List<Person> findByFirstName(String firstName);

    @Modifying
    @Query("update persons set first_name = :name where id = :id")
    boolean updatePerson(Long id, String name);

    @Query("select CalcIncome(:income)")
    Optional<Integer> calculateIncome(int income);

/*
* import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

@Repository
public class HelloWorldRepositoryImpl implements HelloWorldRepositoryCustom {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String callHelloWorld() {
        SimpleJdbcCall jdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PKG_TEST") //package name
                .withFunctionName("HELLO_WORLD"); // name of function or procedure
        SqlParameterSource paramMap = new MapSqlParameterSource()
                .addValue("param", "value"));
        //First parameter is function output parameter type.
        return jdbcCall.executeFunction(String.class, paramMap));
    }

}*/
}

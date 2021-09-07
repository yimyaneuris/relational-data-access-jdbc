package com.relational.dataaccessjdbc.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@RequiredArgsConstructor
@ToString
@Table(value = "persons")
public class Person {

    @Id
    private Long id;
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
}

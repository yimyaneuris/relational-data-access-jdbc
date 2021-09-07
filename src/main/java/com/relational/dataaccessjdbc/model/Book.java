package com.relational.dataaccessjdbc.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import java.math.BigDecimal;

@Getter
@Setter
public class Book {
    @Id
    public int id;
    public String name;
    public BigDecimal price;
}

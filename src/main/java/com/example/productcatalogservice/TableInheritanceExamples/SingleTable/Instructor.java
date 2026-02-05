package com.example.productcatalogservice.TableInheritanceExamples.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "st_instructor")
@DiscriminatorValue(value = "INSTRUCTOR")
public class Instructor extends User {
    private String company;
}

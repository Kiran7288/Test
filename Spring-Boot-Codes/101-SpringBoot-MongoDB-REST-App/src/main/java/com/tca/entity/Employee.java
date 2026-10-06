package com.tca.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "employees")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {
    @Id
    private Integer id;
    private String name;
    private double salary;
}
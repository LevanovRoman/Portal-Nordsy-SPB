package com.myapp.portalnordsyspb.trainingStatistics.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tabNumber;

    private String fullName;

    private String appointName;

    private String department;

    private String departmentTrim;
}
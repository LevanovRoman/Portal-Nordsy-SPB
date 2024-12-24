package com.myapp.portalnordsyspb.suggestionForImprovement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "suggestion")
public class Suggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    int numberRegistration;

    String dateRegistration;

    int department;

    String author;

    boolean registered;

    boolean agreed;

    boolean implemented;
}

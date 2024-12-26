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

    @Column(nullable = true)
    private String numberAndDateRegistration;

    private String department;

    private String author;

    private String registered;

    private String agreed;

    private String implemented;

    @Column(nullable = true)
    private String dateImplementation;

    private String category;
}

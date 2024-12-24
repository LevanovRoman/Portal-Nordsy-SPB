package com.myapp.portalnordsyspb.inspectionCEO.entity;

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
public class Inspection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int department;

    private String date;

    @Enumerated(EnumType.STRING)
    private ScoreColor totalScore;

    @Enumerated(EnumType.STRING)
    private ScoreColor generalScore;

}

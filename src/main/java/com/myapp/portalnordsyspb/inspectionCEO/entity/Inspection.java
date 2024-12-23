package com.myapp.portalnordsyspb.inspectionCEO.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "workshop_id")
    private Workshop workshop;

    private String date;

    @Enumerated(EnumType.STRING)
    private ScoreColor totalScore;

    @Enumerated(EnumType.STRING)
    private ScoreColor generalScore;

}

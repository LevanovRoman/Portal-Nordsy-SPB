package com.myapp.portalnordsyspb.trainingStatistics.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "unit_details")
public class UnitDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "unit_id", referencedColumnName = "id")
    private Unit unit;

    private String date;

    @ElementCollection
    @CollectionTable(name = "details_persons", joinColumns = @JoinColumn(name = "unit_details_id"))
    @Column(name = "persons")
    private List<String> persons;
}

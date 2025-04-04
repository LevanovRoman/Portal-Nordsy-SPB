package com.myapp.portalnordsyspb.trainingStatistics.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "unit_values", joinColumns = @JoinColumn(name = "unit_id"))
    @Column(name = "values")
    private List<Integer> values;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "direction_id")
    private Direction direction;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "period_id")
    private Period period;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "weekday_id")
    private Weekday weekday;

    @OneToOne(mappedBy = "unit", cascade = CascadeType.ALL)
    private UnitDetails unitDetails;

    @Column(columnDefinition = "boolean default false")
    private boolean completed;


    public Unit(int i, Direction direction, Period periodById, Weekday weekdayNotFound) {
    }
}

package com.myapp.portalnordsyspb.trainingStatistics.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myapp.portalnordsyspb.evaluationPU.entity.Area;
import com.myapp.portalnordsyspb.evaluationPU.entity.Criterion;
import com.myapp.portalnordsyspb.evaluationPU.entity.Week;
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
public class Unit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private int value;

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
}

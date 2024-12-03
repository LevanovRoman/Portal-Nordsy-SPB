package com.myapp.portalnordsyspb.trainingStatistics.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myapp.portalnordsyspb.evaluationPU.entity.Result;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Weekday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "weekday")
    private List<Unit> unitList;

}

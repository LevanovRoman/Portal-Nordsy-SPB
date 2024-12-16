package com.myapp.portalnordsyspb.trainingStatistics.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Direction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String remark;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "direction_instructor",
            joinColumns = @JoinColumn(name = "direction_id"),
            inverseJoinColumns = @JoinColumn(name = "instructor_id")
    )
    private List<Instructor> instructors = new ArrayList<>();

    private float hours;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "direction")
    private List<Unit> unitList;

    @PreRemove
    private void removeEntitiesFromJoinTable() {
        instructors.clear();
    }

    @PreUpdate
    private void updateEntitiesFromJoinTable() {
        instructors.clear();
    }
}

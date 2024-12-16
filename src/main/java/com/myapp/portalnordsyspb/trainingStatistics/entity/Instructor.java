package com.myapp.portalnordsyspb.trainingStatistics.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myapp.portalnordsyspb.Temporary.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fio;

    @JsonIgnore
    @ManyToMany(mappedBy = "instructors", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    private List<Direction> directions = new ArrayList<>();
    private Set<Direction> directions = new HashSet<>();

    @PreRemove
    private void removeEntitiesFromJoinTable() {
        directions.clear();
    }
}

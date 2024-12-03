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
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fio;

    @JsonIgnore
    @ManyToMany(mappedBy = "instructors", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Direction> directions = new ArrayList<>();
}

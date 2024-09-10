package com.myapp.portalnordsyspb.entities;

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
public class Criterion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinTable(
//            name = "criterion_area",
//            joinColumns = @JoinColumn(name = "criterion_id"),
//            inverseJoinColumns = @JoinColumn(name = "area_id")
//    )
//    private List<Area> areaList = new ArrayList<>();
//
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "criterion")
//    private List<Result> resultList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "criterion")
    private List<AreaCriterion> areaCriterionList;
}

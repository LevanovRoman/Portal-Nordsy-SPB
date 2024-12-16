package com.myapp.portalnordsyspb.trainingStatistics.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myapp.portalnordsyspb.Temporary.Course;
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
public class Direction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String remark;

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "direction_instructor",
            joinColumns = @JoinColumn(name = "direction_id"),
            inverseJoinColumns = @JoinColumn(name = "instructor_id")
    )
//    private List<Instructor> instructors = new ArrayList<>();
    private Set<Instructor> instructors = new HashSet<>();

    private float hours;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "direction")
    private List<Unit> unitList;

    @PreRemove
    private void removeEntitiesFromJoinTable() {
        instructors.clear();
    }

    public void addInstructor(Instructor instructor) {
        instructors.add(instructor);
        instructor.getDirections().add(this);
    }

//    public void removeInstructor(Instructor instructor) {
//        instructors.remove(instructor);
//        instructor.getDirections().remove(this);
//    }
}

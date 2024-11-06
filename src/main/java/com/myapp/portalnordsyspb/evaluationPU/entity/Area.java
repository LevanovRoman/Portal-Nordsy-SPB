package com.myapp.portalnordsyspb.evaluationPU.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.myapp.portalnordsyspb.level5S.entity.Area5S;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Area implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "department_id")
    private Department department;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "area")
    private List<Result> resultList;

    public static long  getDepartmentId(Area o) {
        return o.getDepartment().getId();
    }
}

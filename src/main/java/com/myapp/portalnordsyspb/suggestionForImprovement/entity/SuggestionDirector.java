package com.myapp.portalnordsyspb.suggestionForImprovement.entity;

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
@Table(name = "suggestion_director")
public class SuggestionDirector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String position;

    @ElementCollection
    @CollectionTable(name = "suggestion_departments", joinColumns = @JoinColumn(name = "suggestion_director_id"))
    @Column(name = "departments")
    private List<String> departmentList;

//    @Column(columnDefinition = "INT DEFAULT 0")
    private int registered = 0;

//    @Column(columnDefinition = "INT DEFAULT 0")
    private int implemented = 0;

//    @Column(columnDefinition = "INT DEFAULT 0")
    private int total = 0;

//    @Column(columnDefinition = "INT DEFAULT 0")
    private int inclusion = 0;
}

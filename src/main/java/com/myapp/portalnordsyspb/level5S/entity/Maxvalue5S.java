package com.myapp.portalnordsyspb.level5S.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Maxvalue5S {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int value;

//    @OneToOne
//    @PrimaryKeyJoinColumn
//    private Area5S area5S;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maxvalue")
    private List<Area5S> area5SList;
}

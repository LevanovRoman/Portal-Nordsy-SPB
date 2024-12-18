package com.myapp.portalnordsyspb.visitCounter.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VisitHistory {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate date;

    private int visitCount;

    public VisitHistory(LocalDate localDate, int count) {
    }
}

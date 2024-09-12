package com.myapp.portalnordsyspb.dto;

import com.myapp.portalnordsyspb.entities.Result;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import java.util.List;

public class WeekDto {

    private int number;

    private List<Result> resultList;
}

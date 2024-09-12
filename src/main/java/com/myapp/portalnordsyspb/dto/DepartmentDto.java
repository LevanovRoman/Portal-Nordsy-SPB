package com.myapp.portalnordsyspb.dto;

import com.myapp.portalnordsyspb.entities.Area;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDto {

    private int department;

    private List<Area> area;

//    private String criterion;
//
//    private int week;
//
//    private int value;
}

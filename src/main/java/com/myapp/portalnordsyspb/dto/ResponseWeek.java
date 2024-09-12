package com.myapp.portalnordsyspb.dto;

import com.myapp.portalnordsyspb.entities.Area;
import com.myapp.portalnordsyspb.entities.Criterion;
import com.myapp.portalnordsyspb.entities.Department;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseWeek {

    private List<Department> department;

    private List<Area> area;

    private List<Criterion> criterion;

    private int week;

    private List<Integer> value;
}

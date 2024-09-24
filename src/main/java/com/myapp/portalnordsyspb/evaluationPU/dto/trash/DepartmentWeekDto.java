package com.myapp.portalnordsyspb.evaluationPU.dto.trash;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentWeekDto {

    private int department;

    int weekNumber;

    private List<AreaWeekDto> area;
}

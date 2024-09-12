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
public class ResultDto {

    private int department;

    private String area;

    private String criterion;

    private int week;

    private int value;
}

package com.myapp.portalnordsyspb.evaluationPU.dto.trash;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultLastWeekDto {

    private String criterion;

    private int value;
}

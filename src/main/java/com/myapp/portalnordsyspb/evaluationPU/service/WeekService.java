package com.myapp.portalnordsyspb.evaluationPU.service;

import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.WeekDto;
import com.myapp.portalnordsyspb.evaluationPU.entity.Week;

import java.util.List;

public interface WeekService {

    Week getTopByOrderByIdDesc();

    List<Week> getTop4ByOrderByIdDesc();

    List<WeekDto> getListWeekDto();
}

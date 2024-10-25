package com.myapp.portalnordsyspb.evaluationPU.service;

import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.ResultResponseDto;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.WeekDto;
import com.myapp.portalnordsyspb.evaluationPU.entity.Week;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface WeekService {

//    Week createWeek();
//
//    Week getLastWeek();

//    Optional<Week> getWeekById(long week_id);

    Week getTopByOrderByIdDesc();

    List<Week> getTop4ByOrderByIdDesc();

    List<WeekDto> getListWeekDto();
}

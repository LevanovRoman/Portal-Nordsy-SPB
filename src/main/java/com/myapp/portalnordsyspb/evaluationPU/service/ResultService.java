package com.myapp.portalnordsyspb.evaluationPU.service;

import com.myapp.portalnordsyspb.evaluationPU.dto.requestDto.WeekRequestDto;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.ResultResponseDto;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.ResultTableFourWeeksDto;
import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.ResultTableLastWeekDto;

import java.util.List;

public interface ResultService {

    List<ResultTableLastWeekDto> getListResultsByAreaIdForLastWeek(Long areaId);

    List<ResultTableFourWeeksDto> getListResultResultTotalFourWeeks(Long areaId);

    void createResultsForWeek(WeekRequestDto weekRequestDto);

    void updateResultsForWeek(WeekRequestDto weekRequestDto, long weekId);

    void deleteResultForWeek(long weekId);

    List<ResultResponseDto> getListResultResponseDtoForWeek(Long weekId);
}

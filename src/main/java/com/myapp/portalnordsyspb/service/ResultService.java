package com.myapp.portalnordsyspb.service;

import com.myapp.portalnordsyspb.dto.requestDto.ResultRequestDto;
import com.myapp.portalnordsyspb.dto.responseDto.ResultTableFourWeeksDto;
import com.myapp.portalnordsyspb.dto.responseDto.ResultTableLastWeekDto;

import java.util.List;

public interface ResultService {

    List<ResultTableLastWeekDto> getListResultsByAreaIdForLastWeek(Long areaId);

    List<ResultTableFourWeeksDto> getListResultResultTotalFourWeeks(Long areaId);

    void addResultsForWeek(List<ResultRequestDto> resultRequestDtoList);

//    List<Result> getAllResults();

//    List<ResultDto> getResultListByAreaId(Long areaId);

//    List<ResultDto> getListResultsByAreaId(Long areaId);

//    List<ResultDto2> getListResultsByAreaId2(Long areaId);

//    List<ResultLastWeekDto> getListResultsByAreaIdAndWeekNumber(Long areaId, int weekNumber);

//    List<Result> createResultWeekSet(List<ResultRequestDto> resultRequestDto);

//    List<Result> getResultFor2Week(Long areaId);

}

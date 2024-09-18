package com.myapp.portalnordsyspb.service;

import com.myapp.portalnordsyspb.dto.ResultDto;
import com.myapp.portalnordsyspb.dto.ResultLastWeekDto;
import com.myapp.portalnordsyspb.dto.ResultTotalFourWeeksDto;

import java.util.List;

public interface ResultService {

//    List<Result> getAllResults();

//    List<ResultDto> getResultListByAreaId(Long areaId);

    List<ResultDto> getListResultsByAreaId(Long areaId);

//    List<ResultDto2> getListResultsByAreaId2(Long areaId);

//    List<ResultLastWeekDto> getListResultsByAreaIdAndWeekNumber(Long areaId, int weekNumber);

    List<ResultLastWeekDto> getListResultsByAreaIdForLastWeek(Long areaId);

    List<ResultTotalFourWeeksDto> getListResultResultTotalFourWeeks(Long areaId);

//    List<Result> getResultFor2Week(Long areaId);
}

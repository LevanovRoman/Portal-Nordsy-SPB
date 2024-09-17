package com.myapp.portalnordsyspb.service;

import com.myapp.portalnordsyspb.dto.ResultDto;
import com.myapp.portalnordsyspb.dto.ResultWeekDto;
import com.myapp.portalnordsyspb.entities.Result;

import java.util.List;

public interface ResultService {

//    List<Result> getAllResults();

//    List<ResultDto> getResultListByAreaId(Long areaId);

    List<ResultDto> getListResultsByAreaId(Long areaId);

    List<ResultWeekDto> getListResultsByAreaIdAndWeekNumber(Long areaId, int weekNumber);

//    List<Result> getResultFor2Week(Long areaId);
}

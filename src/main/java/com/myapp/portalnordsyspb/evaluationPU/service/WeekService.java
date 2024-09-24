package com.myapp.portalnordsyspb.evaluationPU.service;

import com.myapp.portalnordsyspb.evaluationPU.entity.Week;

import java.util.List;
import java.util.Optional;

public interface WeekService {

    Week createWeek();

    Week getLastWeek();

    Optional<Week> getWeekById(long week_id);

    Week getTopByOrderByIdDesc();

    List<Week> getTop4ByOrderByIdDesc();

}

package com.myapp.portalnordsyspb.service;

import com.myapp.portalnordsyspb.dto.ResponseWeek;
import com.myapp.portalnordsyspb.dto.WeekDto;
import com.myapp.portalnordsyspb.entities.Result;
import com.myapp.portalnordsyspb.entities.Week;

import java.util.List;

public interface WeekService {

    List<Week> getAllWeek();

    Week getWeekByNumber(int number);

    List<Result> getResponseByWeekNumber(int weekNum);
}

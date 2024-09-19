package com.myapp.portalnordsyspb.service;

import com.myapp.portalnordsyspb.entities.Week;

public interface WeekService {

    Week createWeek();

    Week getLastWeek();

}

package com.myapp.portalnordsyspb.visitCounter.service;

import com.myapp.portalnordsyspb.visitCounter.entity.VisitHistory;

import java.util.List;

public interface VisitCounterService {
    void incrementVisitCount();

    int getCurrentVisitCount();

    void saveVisitHistory();

    Integer getVisitsForLastWeek();

    Integer getVisitsForLastMonth();

    List<VisitHistory> getDailyVisitsForLastWeek();

    List<VisitHistory> getDailyVisitsForLastMonth();
}
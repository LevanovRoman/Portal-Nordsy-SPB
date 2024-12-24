package com.myapp.portalnordsyspb.visitCounter.controller;

import com.myapp.portalnordsyspb.visitCounter.entity.VisitHistory;
import com.myapp.portalnordsyspb.visitCounter.service.VisitCounterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/counter")
@Tag(name = "Visit Counter", description = "Description for counter")
@RequiredArgsConstructor
public class VisitCounterController {

    private final VisitCounterService visitCounterService;

    // Увеличение счетчика при каждом посещении
    @GetMapping("/visit")
    public void registerVisit() {
        visitCounterService.incrementVisitCount();
    }

    // Получение текущего количества посещений
    @GetMapping("/visit/count")
    public int getCurrentVisitCount() {
        return visitCounterService.getCurrentVisitCount();
    }

    // Получение посещений за последнюю неделю
    @GetMapping("/history/last-week")
    public Integer getVisitsForLastWeek() {
        return visitCounterService.getVisitsForLastWeek();
    }

    // Получение посещений за последний месяц
    @GetMapping("/history/last-month")
    public Integer getVisitsForLastMonth() {
        return visitCounterService.getVisitsForLastMonth();
    }

    @GetMapping("/history/daily/last-week")
    public List<VisitHistory> getDailyVisitsForLastWeek() {
        return visitCounterService.getDailyVisitsForLastWeek();
    }

    @GetMapping("/history/daily/last-month")
    public List<VisitHistory> getDailyVisitsForLastMonth() {
        return visitCounterService.getDailyVisitsForLastMonth();
    }

}


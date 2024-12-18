package com.myapp.portalnordsyspb.visitCounter.controller;

import com.myapp.portalnordsyspb.visitCounter.service.VisitCounterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VisitCounterController {

    private final VisitCounterService visitCounterService;

    // Увеличение счетчика при каждом посещении
    @PostMapping("/visit")
    public void registerVisit() {
        visitCounterService.incrementVisitCount();
    }

    // Получение текущего количества посещений
    @GetMapping("/visit/count")
    public int getCurrentVisitCount() {
        return visitCounterService.getCurrentVisitCount();
    }

    // Получение количества посещений за предыдущий день
    @GetMapping("/visit/previous")
    public int getPreviousDayCount() {
        return visitCounterService.getPreviousDayCount();
    }
}


package com.myapp.portalnordsyspb.controllers;

import com.myapp.portalnordsyspb.dto.ResponseWeek;
import com.myapp.portalnordsyspb.dto.WeekDto;
import com.myapp.portalnordsyspb.entities.Result;
import com.myapp.portalnordsyspb.entities.Week;
import com.myapp.portalnordsyspb.service.WeekService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/week")
@RequiredArgsConstructor
public class WeekController {

    private final WeekService weekService;

    @GetMapping
    ResponseEntity<List<Week>> getAllWeeks(){
        return ResponseEntity.ok(weekService.getAllWeek());
    }

    @GetMapping("/{weekNum}")
    ResponseEntity<Week> getWeekByNumber(@PathVariable("weekNum") int weekNum){
        return ResponseEntity.ok(weekService.getWeekByNumber(weekNum));
    }

    @GetMapping("/collect/{weekNum}")
    ResponseEntity<List<Result>> getResponseByWeekNumber(@PathVariable("weekNum") int weekNum){
        return ResponseEntity.ok(weekService.getResponseByWeekNumber(weekNum));
    }
}

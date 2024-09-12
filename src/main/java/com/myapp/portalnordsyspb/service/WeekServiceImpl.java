package com.myapp.portalnordsyspb.service;

import com.myapp.portalnordsyspb.dto.ResponseWeek;
import com.myapp.portalnordsyspb.dto.WeekDto;
import com.myapp.portalnordsyspb.entities.Result;
import com.myapp.portalnordsyspb.entities.Week;
import com.myapp.portalnordsyspb.repositories.WeekRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeekServiceImpl implements WeekService{

    private final WeekRepository weekRepository;

    @Override
    public List<Week> getAllWeek() {
        return weekRepository.findAll();
    }

    @Override
    public Week getWeekByNumber(int number) {
        return weekRepository.findByNumber(number);
    }

    @Override
    public List<Result> getResponseByWeekNumber(int weekNum) {
        return weekRepository.findResponseByWeekNumber(weekNum);
    }
}

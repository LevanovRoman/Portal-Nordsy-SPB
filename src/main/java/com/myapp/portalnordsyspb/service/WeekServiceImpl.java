package com.myapp.portalnordsyspb.service;

import com.myapp.portalnordsyspb.entities.Week;
import com.myapp.portalnordsyspb.repositories.WeekRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeekServiceImpl implements WeekService{

    private final WeekRepository weekRepository;

    @Override
    public Week createWeek() {
        Week weekNew = new Week();
        Week weekLast = getLastWeek();
        weekNew.setNumber(weekLast.getNumber()+1);
        return weekRepository.save(weekNew);
    }

    @Override
    public Week getLastWeek() {
        return weekRepository.findTopByOrderByIdDesc();
    }
}

package com.myapp.portalnordsyspb.service;

import com.myapp.portalnordsyspb.entities.Week;
import com.myapp.portalnordsyspb.repositories.WeekRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<Week> getWeekById(long week_id) {
        return weekRepository.findById(week_id);
    }

    @Override
    public Week getTopByOrderByIdDesc() {
        return weekRepository.findTopByOrderByIdDesc();
    }

    @Override
    public List<Week> getTop4ByOrderByIdDesc() {
        return weekRepository.findTop4ByOrderByIdDesc();
    }
}

package com.myapp.portalnordsyspb.evaluationPU.service;

import com.myapp.portalnordsyspb.evaluationPU.dto.responseDto.WeekDto;
import com.myapp.portalnordsyspb.evaluationPU.entity.Week;
import com.myapp.portalnordsyspb.evaluationPU.repository.WeekRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WeekServiceImpl implements WeekService{

    private final WeekRepository weekRepository;

//    @Override
//    public Week createWeek() {
//        Week weekNew = new Week();
//        Week weekLast = getLastWeek();
//        weekNew.setNumber(weekLast.getNumber()+1);
//        return weekRepository.save(weekNew);
//    }

//    @Override
//    public Week getLastWeek() {
//        return weekRepository.findTopByOrderByIdDesc();
//    }

//    @Override
//    public Optional<Week> getWeekById(long week_id) {
//        return weekRepository.findById(week_id);
//    }

    @Override
    public Week getTopByOrderByIdDesc() {
        return weekRepository.findTopByOrderByIdDesc();
    }

    @Override
    public List<Week> getTop4ByOrderByIdDesc() {
        return weekRepository.findTop4ByOrderByIdDesc();
    }

    @Override
    public List<WeekDto> getListWeekDto() {
        return weekRepository.findAll()
                .stream()
                .map(this::convertWeekToWeekDto)
                        .toList();
    }

    private WeekDto convertWeekToWeekDto(Week week) {
        return new WeekDto(
                week.getId(),
                week.getWeekName()
        );
    }
}

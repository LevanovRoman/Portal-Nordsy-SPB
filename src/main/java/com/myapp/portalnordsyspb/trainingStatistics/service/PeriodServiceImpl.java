package com.myapp.portalnordsyspb.trainingStatistics.service;

import com.myapp.portalnordsyspb.trainingStatistics.dto.response.PeriodResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.entity.Period;
import com.myapp.portalnordsyspb.trainingStatistics.repository.PeriodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PeriodServiceImpl implements PeriodService{

    private final PeriodRepository periodRepository;
    private final DirectionService directionService;

    @Override
    public List<PeriodResponseDto> getAllPeriodResponseDto() {
        return periodRepository.findAll()
                .stream()
                .map(this::convertPeriodToPeriodResponseDto)
                .toList();
    }

    private PeriodResponseDto convertPeriodToPeriodResponseDto(Period period) {
        return new PeriodResponseDto(
                period.getInterval(),
                period.getMonth(),
                period.getNumber(),
                directionService.getAllDirectionResponseDto(period.getId())
        );
    }
}

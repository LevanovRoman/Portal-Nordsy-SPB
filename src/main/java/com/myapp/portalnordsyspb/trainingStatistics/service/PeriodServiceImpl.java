package com.myapp.portalnordsyspb.trainingStatistics.service;

import com.myapp.portalnordsyspb.exceptions.ObjectNotFoundException;
import com.myapp.portalnordsyspb.trainingStatistics.dto.request.FilterDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.request.PeriodRequestDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.PeriodResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.entity.Period;
import com.myapp.portalnordsyspb.trainingStatistics.repository.PeriodRepository;
import com.myapp.portalnordsyspb.trainingStatistics.repository.WeekdayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PeriodServiceImpl implements PeriodService{

    private final PeriodRepository periodRepository;
    private final DirectionService directionService;

    @Override
    public List<PeriodResponseDto> getAllPeriodResponseDto(FilterDto filterDto) {
        return periodRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Period::getId))
                .map(period -> convertPeriodToPeriodResponseDto(period, filterDto))
                .toList();
    }

    @Override
    public Period getPeriodById(long periodId) {
        return periodRepository.findById(periodId)
                .orElseThrow(() -> new ObjectNotFoundException("Period not found."));
    }

    @Override
    public void createPeriod(PeriodRequestDto periodRequestDto) {
        Period periodNew = new Period();
        savePeriod(periodRequestDto, periodNew);
    }

    @Override
    public void updatePeriod(PeriodRequestDto periodRequestDto, long periodId) {
        Period periodUpdated = getPeriodById(periodId);
        savePeriod(periodRequestDto, periodUpdated);
    }

    @Override
    public void deletePeriod(long periodId) {
        Period periodDeleted = getPeriodById(periodId);
        periodRepository.delete(periodDeleted);
    }

    private void savePeriod(PeriodRequestDto periodRequestDto, Period period){
        period.setInterval(periodRequestDto.interval());
        period.setMonth(periodRequestDto.month());
        period.setNumber(periodRequestDto.number());
        periodRepository.save(period);
    }

    private PeriodResponseDto convertPeriodToPeriodResponseDto(Period period, FilterDto filterDto) {
        return new PeriodResponseDto(
                period.getId(),
                period.getInterval(),
                period.getMonth(),
                period.getNumber(),
                directionService.getAllDirectionResponseDto(period.getId(), filterDto)
        );
    }
}

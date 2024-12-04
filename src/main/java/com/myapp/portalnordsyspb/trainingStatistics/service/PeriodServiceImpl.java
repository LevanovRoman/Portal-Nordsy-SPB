package com.myapp.portalnordsyspb.trainingStatistics.service;

import com.myapp.portalnordsyspb.exceptions.ObjectNotFoundException;
import com.myapp.portalnordsyspb.trainingStatistics.dto.request.PeriodRequestDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.request.UnitRequestDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.PeriodResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.entity.Direction;
import com.myapp.portalnordsyspb.trainingStatistics.entity.Period;
import com.myapp.portalnordsyspb.trainingStatistics.entity.Unit;
import com.myapp.portalnordsyspb.trainingStatistics.entity.Weekday;
import com.myapp.portalnordsyspb.trainingStatistics.repository.PeriodRepository;
import com.myapp.portalnordsyspb.trainingStatistics.repository.WeekdayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PeriodServiceImpl implements PeriodService{

    private final PeriodRepository periodRepository;
    private final DirectionService directionService;
    private final WeekdayRepository weekdayRepository;

    @Override
    public List<PeriodResponseDto> getAllPeriodResponseDto() {
        return periodRepository.findAll()
                .stream()
                .map(this::convertPeriodToPeriodResponseDto)
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
        List<Unit> unitList = periodRequestDto.unitRequestDtoList()
                .stream()
                .map(unit -> convertUnitRequestDtoToUnit(unit, period))
                .toList();
        period.setInterval(periodRequestDto.interval());
        period.setMonth(periodRequestDto.month());
        period.setNumber(periodRequestDto.number());
        period.setUnitList(unitList);
        periodRepository.save(period);
    }

    private Unit convertUnitRequestDtoToUnit(UnitRequestDto unitRequestDto, Period period) {
        Direction direction = directionService.getDirectionById(unitRequestDto.directionId());
        Weekday weekday = weekdayRepository.findById(unitRequestDto.weekdayId())
                .orElseThrow(() -> new ObjectNotFoundException("Weekday not found."));
        return Unit.builder()
                .value(unitRequestDto.value())
                .direction(direction)
                .period(period)
                .weekday(weekday)
                .build();
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

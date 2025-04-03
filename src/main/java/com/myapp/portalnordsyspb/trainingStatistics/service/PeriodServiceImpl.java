package com.myapp.portalnordsyspb.trainingStatistics.service;

import com.myapp.portalnordsyspb.exceptions.ObjectNotFoundException;
import com.myapp.portalnordsyspb.trainingStatistics.dto.request.FilterDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.request.PeriodRequestDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.DirectionNameDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.PeriodResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.PersonsByDirectionDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.PersonsByDirectionsForMonthDto;
import com.myapp.portalnordsyspb.trainingStatistics.entity.Period;
import com.myapp.portalnordsyspb.trainingStatistics.repository.PeriodRepository;
import com.myapp.portalnordsyspb.trainingStatistics.repository.UnitDetailsRepository;
import com.myapp.portalnordsyspb.trainingStatistics.repository.WeekdayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PeriodServiceImpl implements PeriodService{

    private final PeriodRepository periodRepository;
    private final DirectionService directionService;
    private final UnitDetailsRepository unitDetailsRepository;

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

    @Override
    public List<PersonsByDirectionsForMonthDto> getPersonsByDirectionsForMonth(int year) {
        Map<Integer, String> monthMap = new HashMap<>();
        monthMap.put(1, "Январь");
        monthMap.put(2, "Февраль");
        monthMap.put(3, "Март");
        monthMap.put(4, "Апрель");
        monthMap.put(5, "Май");
        monthMap.put(6, "Июнь");
        monthMap.put(7, "Июль");
        monthMap.put(8, "Август");
        monthMap.put(9, "Сентябрь");
        monthMap.put(10, "Октябрь");
        monthMap.put(11, "Ноябрь");
        monthMap.put(12, "Декабрь");
        List<PersonsByDirectionsForMonthDto> resultList = new ArrayList<>();
        List<DirectionNameDto> directionNameDtoList = directionService.getAllDirectionsNames();
        for (int i = 1; i <= 12; i++) {
            List<PersonsByDirectionDto> personsByDirectionDtoList = new ArrayList<>();
            for (DirectionNameDto direction : directionNameDtoList){
                personsByDirectionDtoList.add(new PersonsByDirectionDto(
                        direction.name(),
                        unitDetailsRepository.countPersonsForMonthForDirection(i, direction.id(), year)
                ));
            }
            resultList.add(new PersonsByDirectionsForMonthDto(
                    i,
                    monthMap.get(i),
                    personsByDirectionDtoList
            ));
        }
        return resultList;
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

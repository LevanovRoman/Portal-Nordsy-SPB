package com.myapp.portalnordsyspb.trainingStatistics.service;

import com.myapp.portalnordsyspb.exceptions.ObjectNotFoundException;
import com.myapp.portalnordsyspb.trainingStatistics.dto.request.FilterDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.request.UnitRequestDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.UnitResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.entity.Direction;
import com.myapp.portalnordsyspb.trainingStatistics.entity.Unit;
import com.myapp.portalnordsyspb.trainingStatistics.entity.Weekday;
import com.myapp.portalnordsyspb.trainingStatistics.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UnitServiceAlternative implements UnitService{

    private final UnitRepository unitRepository;
    private final PeriodRepository periodRepository;
    private final WeekdayRepository weekdayRepository;
    private final UnitDetailsRepository unitDetailsRepository;
    private final PersonRepository personRepository;

    @Override
    public List<UnitResponseDto> getUnitResponseDtoByPeriodIdAndDirectionId(Long period_id, Direction direction,
                                                                            FilterDto filterDto) {
        List<UnitResponseDto> unitResponseDtoList = new ArrayList<>();
        boolean hasTubNumber = filterDto.tubNumber() != null;
        boolean hasUnitValue = filterDto.unitValue() != null;
        // Комбинируем состояния в String ключ для switch-case
        String state = (hasTubNumber ? "TUB" : "") + (hasUnitValue ? "UNIT" : "");
        List<Weekday> weekdayList = weekdayRepository.findAll();
        Optional<Unit> unit;
        UnitResponseDto unitResponseDto;
        for (Weekday weekday : weekdayList){
            switch (state) {
                case "TUBUNIT":{
                    unit = unitRepository.findFilterByTabNumberAndUnitValue(direction.getId(), period_id,
                            weekday.getId(), filterDto.unitValue(), filterDto.tubNumber());
                    unitResponseDto = checkEmptyUnit(weekday, unit);
                    break;
                }

                case "TUB":{
                    unit = unitRepository.findFilterByTubNumber(direction.getId(), period_id,
                            weekday.getId(), filterDto.tubNumber());
                    unitResponseDto = checkEmptyUnit(weekday, unit);
                    break;
                }

                case "UNIT":{
                    unit = unitRepository.findFilterByUnitValue(direction.getId(), period_id,
                            weekday.getId(), filterDto.unitValue());
                    unitResponseDto = checkEmptyUnit(weekday, unit);
                    break;
                }
                default:{
                    unit = unitRepository.findByDirectionIdAndPeriodIdAndWeekdayId(direction.getId(), period_id,
                            weekday.getId());
                    unitResponseDto = checkEmptyUnitAndCreate(period_id, direction, weekday, unit);
                    break;
                }
            }
            unitResponseDtoList.add(unitResponseDto);
        }
        return unitResponseDtoList;
    }

    @Override
    public Unit getUnitById(long unitId) {
        return null;
    }

    @Override
    public void updateUnit(UnitRequestDto unitRequestDto, long unitId) {

    }

    @Override
    public void deleteUnit(long unitId) {

    }

    private UnitResponseDto checkEmptyUnit(Weekday weekday, Optional<Unit> unitOptional) {
        UnitResponseDto unitResponseDto;
        if (unitOptional.isEmpty()){
            List<Integer> values = new ArrayList<>();
            values.add(0);
            unitResponseDto = new UnitResponseDto(weekday.getName(), 0,
                    false, values);
        } else unitResponseDto = new UnitResponseDto(weekday.getName(), unitOptional.get().getId(),
                unitOptional.get().isCompleted(), unitOptional.get().getValues());
        return unitResponseDto;
    }

    private UnitResponseDto checkEmptyUnitAndCreate(long period_id, Direction direction, Weekday weekday,
                                                    Optional<Unit> unit){
        UnitResponseDto unitResponseDto;
        if (unit.isEmpty()){
            unit = Optional.of(getNewUnit(period_id, direction, weekday));
        }
        return new UnitResponseDto(weekday.getName(), unit.get().getId(),
                unit.get().isCompleted(), unit.get().getValues());
    }

//    @Override
//    public Unit getUnitById(long unitId) {
//        return null;
//    }
//
//    @Override
//    public void updateUnit(UnitRequestDto unitRequestDto, long unitId) {
//
//    }
//
//    @Override
//    public void deleteUnit(long unitId) {
//
//    }
    private Unit getNewUnit(long period_id, Direction direction, Weekday weekday) {
        Unit unitNew = new Unit();
        unitNew.setDirection(direction);
        unitNew.setPeriod(periodRepository.findById(period_id)
                .orElseThrow(() -> new ObjectNotFoundException("Period not found.")));
        unitNew.setWeekday(weekday);
        List<Integer> values = new ArrayList<>();
        values.add(0);
        unitNew.setValues(values);
        return unitRepository.save(unitNew);
    }

}

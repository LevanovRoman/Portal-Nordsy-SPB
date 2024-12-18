package com.myapp.portalnordsyspb.trainingStatistics.service;

import com.myapp.portalnordsyspb.exceptions.ObjectNotFoundException;
import com.myapp.portalnordsyspb.trainingStatistics.dto.request.FilterDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.request.UnitRequestDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.UnitResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.entity.*;
import com.myapp.portalnordsyspb.trainingStatistics.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Service
@RequiredArgsConstructor
public class UnitServiceImpl implements UnitService{

    private final UnitRepository unitRepository;
    private final PeriodRepository periodRepository;
    private final WeekdayRepository weekdayRepository;
    private final UnitDetailsRepository unitDetailsRepository;
    private final PersonRepository personRepository;

    @Override
    public List<UnitResponseDto> getUnitResponseDtoByPeriodIdAndDirectionId(Long period_id, Direction direction,
                                                                            FilterDto filterDto) {
        List<UnitResponseDto> unitResponseDtoList = new ArrayList<>();
        for (long i = 1; i <=5 ; i++) {
            Weekday weekday = weekdayRepository.findById(i)
                    .orElseThrow(() -> new ObjectNotFoundException("Weekday not found"));
            Optional<Unit> unit = unitRepository.findByDirectionIdAndPeriodIdAndWeekdayId(direction.getId(), period_id, i);
            if (unit.isEmpty()){
                unit = Optional.of(getNewUnit(period_id, direction, weekday));
            }
            unitResponseDtoList.add(new UnitResponseDto(weekday.getName(), unit.get().getId(),
                    unit.get().isCompleted(), unit.get().getValues()));
        }
        return unitResponseDtoList;
    }

    @Override
    public Unit getUnitById(long unitId) {
        return unitRepository.findById(unitId)
                .orElseThrow(() -> new ObjectNotFoundException("Unit not found."));
    }

    @Override
    public void updateUnit(UnitRequestDto unitRequestDto, long unitId) {
        Unit unit = getUnitById(unitId);
        unit.setValues(unitRequestDto.values());
        if (unitRequestDto.completed()){
            unit.setCompleted(true);
            Optional<UnitDetails> unitDetails = unitDetailsRepository.findByUnitId(unitId);
            if (unitDetails.isEmpty()){
                UnitDetails unitDetailsNew = new UnitDetails();
                saveUnitDetails(unitDetailsNew, unitRequestDto, unit);
            } else {
                saveUnitDetails(unitDetails.get(), unitRequestDto, unit);
            }
        }
        unitRepository.save(unit);
    }

    @Override
    public void deleteUnit(long unitId) {
        Unit unitDeleted = getUnitById(unitId);
        Optional<UnitDetails> unitDetails = unitDetailsRepository.findByUnitId(unitId);
        unitDetails.ifPresent(unitDetailsRepository::delete);
        List<Integer> values = new ArrayList<>();
        values.add(0);
        unitDeleted.setValues(values);
    }

    private void saveUnitDetails(UnitDetails unitDetails, UnitRequestDto unitRequestDto, Unit unit){
        unitDetails.setDate(unitRequestDto.date());
        unitDetails.setUnit(unit);
        unitDetails.setPersons(getPersonsList(unitRequestDto.tabNumberList()));
        unitDetailsRepository.save(unitDetails);
    }

    private List<String> getPersonsList(List<String> tabNumberList){
        List<String> personList = new ArrayList<>();
        for (String tabNumber : tabNumberList){
            Person person = personRepository.findByTabNumber(tabNumber)
                    .orElseThrow(() -> new ObjectNotFoundException("Person not found."));
            String[] details = {person.getTabNumber(), person.getFullName(), person.getAppointName()};
            String personString = String.join(",", details);
            personList.add(personString);
        }
        return personList;
    }

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

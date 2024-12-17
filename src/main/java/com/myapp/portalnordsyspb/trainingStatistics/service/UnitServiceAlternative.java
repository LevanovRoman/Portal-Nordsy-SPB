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
                    .orElseThrow(() -> new ObjectNotFoundException("Person not found with number: " + tabNumber));
            String[] details = {person.getTabNumber(), person.getFullName(), person.getAppointName()};
            String personString = String.join(",", details);
            personList.add(personString);
        }
        return personList;
    }
}

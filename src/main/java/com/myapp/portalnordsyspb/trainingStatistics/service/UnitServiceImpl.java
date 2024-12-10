package com.myapp.portalnordsyspb.trainingStatistics.service;

import com.myapp.portalnordsyspb.exceptions.ObjectNotFoundException;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.UnitResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.entity.Direction;
import com.myapp.portalnordsyspb.trainingStatistics.entity.Unit;
import com.myapp.portalnordsyspb.trainingStatistics.entity.Weekday;
import com.myapp.portalnordsyspb.trainingStatistics.repository.PeriodRepository;
import com.myapp.portalnordsyspb.trainingStatistics.repository.UnitRepository;
import com.myapp.portalnordsyspb.trainingStatistics.repository.WeekdayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UnitServiceImpl implements UnitService{

    private final UnitRepository unitRepository;
    private final PeriodRepository periodRepository;
    private final WeekdayRepository weekdayRepository;

    @Override
    public List<UnitResponseDto> getUnitResponseDtoByPeriodIdAndDirectionId(Long period_id, Direction direction) {
        List<UnitResponseDto> unitResponseDtoList = new ArrayList<>();
        for (long i = 1; i <=5 ; i++) {
            Weekday weekday = weekdayRepository.findById(i)
                    .orElseThrow(() -> new ObjectNotFoundException("Weekday not found"));
            Optional<Unit> unit = unitRepository.findByDirectionIdAndPeriodIdAndWeekdayId(direction.getId(), period_id, i);
            if (unit.isEmpty()){
                unit = Optional.of(getNewUnit(period_id, direction, weekday));
            }
            unitResponseDtoList.add(new UnitResponseDto(weekday.getName(), unit.get().getId(),
                    unit.get().isCompleted(), unit.get().getValue()));
        }
        return unitResponseDtoList;
    }

    @Override
    public Unit getUnitById(long unitId) {
        return unitRepository.findById(unitId)
                .orElseThrow(() -> new ObjectNotFoundException("Unit not found."));
    }

    private Unit getNewUnit(long period_id, Direction direction, Weekday weekday) {
        Unit unitNew = new Unit();
        unitNew.setDirection(direction);
        unitNew.setPeriod(periodRepository.findById(period_id)
                .orElseThrow(() -> new ObjectNotFoundException("Period not found.")));
        unitNew.setWeekday(weekday);
        unitNew.setValue(0);
        return unitRepository.save(unitNew);
    }
}

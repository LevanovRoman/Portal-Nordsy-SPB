package com.myapp.portalnordsyspb.trainingStatistics.service;

import com.myapp.portalnordsyspb.trainingStatistics.dto.response.UnitResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.entity.Unit;
import com.myapp.portalnordsyspb.trainingStatistics.repository.UnitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UnitServiceImpl implements UnitService{

    private final UnitRepository unitRepository;

    @Override
    public UnitResponseDto getUnitResponseDtoByPeriodIdAndDirectionId(Long period_id, Long direction_id) {
        List<Integer> results = new ArrayList<>();
        for (long i = 1; i <=5 ; i++) {
            Optional<Unit> unit = unitRepository.findByDirectionIdAndPeriodIdAndWeekdayId(direction_id, period_id, i);
            if (unit.isEmpty()){
                results.add(0);
            } else {
                results.add(unit.get().getValue());
            }
        }
        return new UnitResponseDto(results);
    }
}

package com.myapp.portalnordsyspb.trainingStatistics.service;

import com.myapp.portalnordsyspb.exceptions.ObjectNotFoundException;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.DirectionResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.entity.Direction;
import com.myapp.portalnordsyspb.trainingStatistics.repository.DirectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectionServiceImpl implements DirectionService{

    private final DirectionRepository directionRepository;
    private final InstructorService instructorService;
    private final UnitService unitService;


    @Override
    public List<DirectionResponseDto> getAllDirectionResponseDto(Long period_id) {
        return directionRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Direction::getId))
                .map(direction -> convertDirectionToDirectionResponseDto(direction, period_id))
                .toList();
    }

    @Override
    public Direction getDirectionById(long directionId) {
        return directionRepository.findById(directionId)
                .orElseThrow(() -> new ObjectNotFoundException("Direction not found."));
    }

    private DirectionResponseDto convertDirectionToDirectionResponseDto(Direction direction, Long periodId) {
        return new DirectionResponseDto(
                direction.getId(),
                direction.getName(),
                direction.getRemark(),
                direction.getHours(),
                instructorService.getAllByDirectionId(direction.getId()),
                unitService.getUnitResponseDtoByPeriodIdAndDirectionId(periodId, direction)
        );
    }
}

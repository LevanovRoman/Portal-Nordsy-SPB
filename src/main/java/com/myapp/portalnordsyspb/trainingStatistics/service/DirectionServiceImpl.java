package com.myapp.portalnordsyspb.trainingStatistics.service;

import com.myapp.portalnordsyspb.exceptions.ObjectNotFoundException;
import com.myapp.portalnordsyspb.trainingStatistics.dto.request.DirectionRequestDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.request.FilterDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.DirectionNameDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.DirectionOnlyResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.DirectionResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.entity.Direction;
import com.myapp.portalnordsyspb.trainingStatistics.entity.Instructor;
import com.myapp.portalnordsyspb.trainingStatistics.repository.DirectionRepository;
import com.myapp.portalnordsyspb.trainingStatistics.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class DirectionServiceImpl implements DirectionService{

    private final DirectionRepository directionRepository;
    private final InstructorRepository instructorRepository;
    private final InstructorService instructorService;
    private final UnitService unitService;

    @Override
    public List<DirectionResponseDto> getAllDirectionResponseDto(Long period_id, FilterDto filterDto) {
        return directionRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Direction::getId))
                .map(direction -> convertDirectionToDirectionResponseDto(direction, period_id, filterDto))
                .toList();
    }

    @Override
    public List<DirectionOnlyResponseDto> getAllDirections() {
        return directionRepository.findAll()
                .stream()
                .map(this::convertDirectionToDirectionOnlyResponseDto)
                .toList();
    }

    @Override
    public List<DirectionNameDto> getAllDirectionsNames() {
        return directionRepository.findAll()
                .stream()
                .map(this::convertDirectionToDirectionNameDto)
                .toList();
    }

    private DirectionNameDto convertDirectionToDirectionNameDto(Direction direction) {
        return new DirectionNameDto(direction.getId(), direction.getName());
    }

    private DirectionOnlyResponseDto convertDirectionToDirectionOnlyResponseDto(Direction direction) {
        return new DirectionOnlyResponseDto(
                direction.getId(),
                direction.getName(),
                direction.getRemark(),
                direction.getHours(),
                instructorService.getAllByDirectionId(direction.getId())
        );
    }

    @Override
    public Direction getDirectionById(long directionId) {
        return directionRepository.findById(directionId)
                .orElseThrow(() -> new ObjectNotFoundException("Direction not found."));
    }

    @Override
    public void createDirection(DirectionRequestDto directionRequestDto) {
        Direction directionNew = new Direction();
        saveDirection(directionRequestDto, directionNew);
    }

    @Override
    public void updateDirection(DirectionRequestDto directionRequestDto, long directionId) {
        Direction directionUpdated = getDirectionById(directionId);
        saveDirection(directionRequestDto, directionUpdated);
    }

    @Override
    public void deleteDirection(long directionId) {
        directionRepository.delete(getDirectionById(directionId));
    }

    private void saveDirection(DirectionRequestDto directionRequestDto, Direction direction){
        direction.setName(directionRequestDto.name());
        direction.setRemark(directionRequestDto.remark());
        direction.setHours(directionRequestDto.hours());
        Set<Long> ids = directionRequestDto.instructorIdSet(); // Получение идентификаторов
        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("Список идентификаторов не может быть пустым или null");
        }
        Set<Instructor> instructors = new HashSet<>(instructorRepository.
                findAllById(ids));
        direction.setInstructors(instructors);
        directionRepository.save(direction);
    }

    private Instructor convertIdToInstructor(Long id) {
        return instructorService.getInstructorById(id);
    }

    private DirectionResponseDto convertDirectionToDirectionResponseDto(Direction direction, Long periodId,
                                                                        FilterDto filterDto) {
        return new DirectionResponseDto(
                direction.getId(),
                direction.getName(),
                direction.getRemark(),
                direction.getHours(),
                instructorService.getAllByDirectionId(direction.getId()),
                unitService.getUnitResponseDtoByPeriodIdAndDirectionId(periodId, direction, filterDto)
        );
    }

    @Transactional
    public void removeEntityFromRelation(Long directionId, Long entity2Id) {
        Direction entity1 = getDirectionById(directionId);
    }
}

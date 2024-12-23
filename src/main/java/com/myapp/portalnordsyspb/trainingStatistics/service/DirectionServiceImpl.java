package com.myapp.portalnordsyspb.trainingStatistics.service;

import com.myapp.portalnordsyspb.exceptions.ObjectNotFoundException;
import com.myapp.portalnordsyspb.trainingStatistics.dto.request.DirectionRequestDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.request.FilterDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.DirectionOnlyResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.DirectionResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.DirectionUpdateResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.entity.Direction;
import com.myapp.portalnordsyspb.trainingStatistics.entity.Instructor;
import com.myapp.portalnordsyspb.trainingStatistics.repository.DirectionRepository;
import com.myapp.portalnordsyspb.trainingStatistics.repository.InstructorRepository;
import jakarta.persistence.EntityManager;
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
//    private final EntityManager entityManager;


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

//    @Override
//    public DirectionUpdateResponseDto getDirection(long directionId) {
//        Direction direction = getDirectionById(directionId);
//        return new DirectionUpdateResponseDto(
//                direction.getId(),
//                direction.getName(),
//                direction.getRemark(),
//                direction.getHours(),
//                instructorService.getAllByDirectionId(direction.getId())
//        );
//    }

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

//    @Override
//    public void updateDirection(DirectionRequestDto directionRequestDto, long directionId) {
//        Direction directionUpdated = getDirectionById(directionId);
//        saveDirection(directionRequestDto, directionUpdated);
//    }

    @Override
    public void updateDirection(DirectionRequestDto directionRequestDto, long directionId) {
        System.out.println("SERVICE" + directionId);
        Direction directionUpdated = getDirectionById(directionId);
//        directionUpdated.setName(directionRequestDto.name());
//        directionUpdated.setRemark(directionRequestDto.remark());
//        directionUpdated.setHours(directionRequestDto.hours());
//
//        Set<Instructor> instructors = new HashSet<>(instructorRepository.
//                findAllById(directionRequestDto.instructorIdSet()));
//        directionUpdated.getInstructors().clear();
//        for (Instructor instructor : instructors){
//            directionUpdated.addInstructor(instructor);
//        }
//        directionRepository.save(directionUpdated);
        saveDirection(directionRequestDto, directionUpdated);
    }

    @Override
    public void deleteDirection(long directionId) {
        directionRepository.delete(getDirectionById(directionId));
    }

    //    private void saveDirection(DirectionRequestDto directionRequestDto, Direction direction){
//        direction.setName(directionRequestDto.name());
//        direction.setRemark(directionRequestDto.remark());
//        direction.setHours(directionRequestDto.hours());
//        if (! directionRequestDto.instructorIdList().isEmpty()){
//            List<Instructor> instructorList = directionRequestDto.instructorIdList()
//                    .stream()
//                    .map(this::convertIdToInstructor)
//                    .toList();
//            direction.setInstructors(new HashSet<>(instructorList));
//        }
//        directionRepository.save(direction);
//    }
    private void saveDirection(DirectionRequestDto directionRequestDto, Direction direction){
        direction.setName(directionRequestDto.name());
        direction.setRemark(directionRequestDto.remark());
        direction.setHours(directionRequestDto.hours());
        Set<Long> ids = directionRequestDto.instructorIdSet(); // Получение идентификаторов
        if (ids == null || ids.isEmpty()) {
            throw new IllegalArgumentException("Список идентификаторов не может быть пустым или null");
        }
        System.out.println("direction.getInstructors()" + direction.getInstructors());
        System.out.println("Переданные идентификаторы: {}" +  ids);
        Set<Instructor> instructors = new HashSet<>(instructorRepository.
                findAllById(ids));
        direction.setInstructors(instructors);
//        direction.getInstructors().clear();
//        for (Instructor instructor : instructors){
//            direction.addInstructor(instructor);
//        }
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

    //    @Transactional
//    public void deleteFromJoinTable(Long entity1Id, Long entity2Id) {
//        entityManager.createNativeQuery("DELETE FROM entity1_entity2 WHERE entity1_id = :entity1Id AND entity2_id = :entity2Id")
//                .setParameter("entity1Id", entity1Id)
//                .setParameter("entity2Id", entity2Id)
//                .executeUpdate();
//    }

//    @Transactional
//    public void deleteFromJoinTable(Long directionId) {
//        entityManager.createNativeQuery("DELETE FROM direction_instructor WHERE direction_id = :directionId")
//                .setParameter("directionId", directionId)
//                .executeUpdate();
//    }

    @Transactional
    public void removeEntityFromRelation(Long directionId, Long entity2Id) {
        Direction entity1 = getDirectionById(directionId);
//        Entity2 entity2 = entity2Repository.findById(entity2Id).orElseThrow();

        // Удаляем связь
//        entity1.getInstructors().remove(entity2);
//        entity1Repository.save(entity1);
    }
}

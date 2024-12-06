package com.myapp.portalnordsyspb.trainingStatistics.service;

import com.myapp.portalnordsyspb.exceptions.ObjectNotFoundException;
import com.myapp.portalnordsyspb.trainingStatistics.dto.request.InstructorRequestDto;
import com.myapp.portalnordsyspb.trainingStatistics.dto.response.InstructorResponseDto;
import com.myapp.portalnordsyspb.trainingStatistics.entity.Instructor;
import com.myapp.portalnordsyspb.trainingStatistics.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService{

    private final InstructorRepository instructorRepository;

    @Override
    public List<InstructorResponseDto> getAllByDirectionId(Long direction_id) {
        return instructorRepository.findAllByDirectionId(direction_id)
                .stream()
                .map(this::convertInstructorToInstructorResponseDto)
                .toList();
    }

    @Override
    public List<InstructorResponseDto> getAllInstructor() {
        return List.of();
    }

    @Override
    public void createInstructor(InstructorRequestDto instructorRequestDto) {
        saveInstructor(new Instructor(), instructorRequestDto);
    }

    @Override
    public void updateInstructor(InstructorRequestDto instructorRequestDto, long instructorId) {
        saveInstructor(getInstructorById(instructorId), instructorRequestDto);
    }

    @Override
    public void deleteInstructor(long instructorId) {
        instructorRepository.delete(getInstructorById(instructorId));
    }

    private void saveInstructor(Instructor instructor, InstructorRequestDto instructorRequestDto){
        instructor.setFio(instructorRequestDto.fio());
        instructorRepository.save(instructor);
    }

    private Instructor getInstructorById(long instructorId){
        return instructorRepository.findById(instructorId)
                .orElseThrow(() -> new ObjectNotFoundException("Instructor not found."));
    }

    private InstructorResponseDto convertInstructorToInstructorResponseDto(Instructor instructor) {
        return new InstructorResponseDto(instructor.getId(), instructor.getFio());
    }
}

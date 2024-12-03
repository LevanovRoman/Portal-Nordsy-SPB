package com.myapp.portalnordsyspb.trainingStatistics.service;

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

//    @Override
//    public List<InstructorResponseDto> getAllInstructorResponseDto() {
//        return instructorRepository.findAll()
//                .stream()
//                .map(this::convertInstructorToInstructorResponseDto)
//                .toList();
//    }

    @Override
    public List<InstructorResponseDto> getAllByDirectionId(Long direction_id) {
        return instructorRepository.findAllByDirectionId(direction_id)
                .stream()
                .map(this::convertInstructorToInstructorResponseDto)
                .toList();
    }

    private InstructorResponseDto convertInstructorToInstructorResponseDto(Instructor instructor) {
        return new InstructorResponseDto(instructor.getFio());
    }
}
